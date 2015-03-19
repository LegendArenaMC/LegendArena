package net.thenamedev.legendapi.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * [ Code used in this class can be found here: https://gist.github.com/Techcable/c6a5caad442a220b4dab ]
 *
 * Represents a 1.8 title
 *
 * Supports Real 1.8 and Fake 1.8
 *
 * @author Techcable
 */
public class Title {

    public static final int RESET_ACTION = 4;
    public static final int SET_TITLE_ACTION = 0;
    public static final int SET_SUBTITLE_ACTION = 1;
    public static final int DISPLAY_ACTION = 2;

    public Title() {}

    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public Title(String title, String subtitle) {
        this(title, subtitle, 20, 100, 20);
    }

    private String title;
    private String subtitle;
    private int fadeIn, stay, fadeOut = 100; //100 Ticks each default

    /**
     * Display the players this title
     * Only shows players who are on 1.8
     *
     * @param players players to display this title to
     */
    public void sendTo(Player... players) {
        for (Player player : players) {
            if (!is1_8(player)) continue;
            sendPacket(createTitlePacket(RESET_ACTION), player);
            if (title != null && !title.isEmpty()) {
                sendPacket(createTitlePacket(SET_TITLE_ACTION, title), player);
            }
            if (subtitle != null && !subtitle.isEmpty()) {
                sendPacket(createTitlePacket(SET_SUBTITLE_ACTION, subtitle), player);
            }
            if (title != null && !title.isEmpty() || subtitle != null && !subtitle.isEmpty()) {
                sendPacket(createTitlePacket(DISPLAY_ACTION, fadeIn, stay, fadeOut), player);
            }
        }
    }

    //Packet Code

    private static boolean is1_8(Player player) {
        if (getNmsClass("TitlePacket") != null) return true; //Real 1.8
        Object handle = getHandle(player);
        Field playerConnectionField = makeField(handle.getClass(), "playerConnection");
        Object connection = getField(playerConnectionField, handle);
        Field networkManagerField = makeField(connection.getClass(), "networkManager");
        Object networkManager = getField(networkManagerField, connection);
        Method getVersion = makeMethod(networkManager.getClass(), "getVersion");
        if (getVersion() == null) return false; //Not Fake 1.8
        int version = callMethod(getVersion, networkManager);
        return version >= 47;
    }

    private static Object createTitlePacket(int action) {
        Class<?> titlePacketClass = getNmsClass("TitlePacket");
        if (titlePacketClass == null) { //Not Real 1.8
            titlePacketClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle");
            Class<?> actionClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle$Action");
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, actionClass);
            return callConstructor(chatConstructor, createAction(action));
        } else {
            Class<?> actionClass = getNmsClass("EnumTitleAction");
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, actionClass, getNmsClass("IChatBaseComponent"));
            return callConstructor(chatConstructor, createAction(action), null);
        }
    }

    private static Object createTitlePacket(int action, int fadeIn, int stay, int fadeOut) {
        Class<?> titlePacketClass = getNmsClass("TitlePacket");
        if (titlePacketClass == null) { //Not Real 1.8
            titlePacketClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle");
            Class<?> actionClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle$Action");
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, actionClass, int.class, int.class, int.class);
            return callConstructor(chatConstructor, createAction(action), fadeIn, stay, fadeOut);
        } else {
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, int.class, int.class, int.class);
            return callConstructor(chatConstructor, createAction(action), fadeIn, stay, fadeOut);
        }
    }

    private static Object createTitlePacket(int action, String text) {
        Class<?> titlePacketClass = getNmsClass("TitlePacket");
        if (titlePacketClass == null) { //Not Real 1.8
            titlePacketClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle");
            Class<?> actionClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle$Action");
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, actionClass, getNmsClass("IChatBaseComponent"));
            return callConstructor(chatConstructor, createAction(action), toBaseComponents(text)[0]);
        } else {
            Class<?> actionClass = getNmsClass("EnumTitleAction");
            Constructor<?> chatConstructor = makeConstructor(titlePacketClass, actionClass, getNmsClass("IChatBaseComponent"));
            return callConstructor(chatConstructor, createAction(action), toBaseComponents(text)[0]);
        }
    }

    private static Object createAction(int id) {
        Class<?> actionClass = getNmsClass("EnumTitleAction");
        if (actionClass == null) {
            actionClass = getClass("org.spigotmc.ProtocolInjector$PacketTitle$Action");
            return actionClass.getEnumConstants()[id];
        } else {
            return actionClass.getEnumConstants()[id];
        }
    }

    private static Object[] toBaseComponents(String chat) {
        Class<?> craftChatMessage = getCbClass("util.CraftChatMessage");
        Method fromString = makeMethod(craftChatMessage, "fromString", String.class);
        return callMethod(fromString, null, chat);
    }

    private static void sendPacket(Object packet, Player player) {
        Object handle = getHandle(player);
        Field playerConnectionField = makeField(handle.getClass(), "playerConnection");
        Object connection = getField(playerConnectionField, handle);
        Method sendPacket = makeMethod(connection.getClass(), "sendPacket", getNmsClass("Packet"));
        callMethod(sendPacket, connection, packet);
    }

    //NMS Utils

    private static Class<?> getNmsClass(String name) {
        String className = "net.minecraft.server." + getVersion() + "." + name;
        return getClass(className);
    }

    private static Class<?> getCbClass(String name) {
        String className = "org.bukkit.craftbukkit." + getVersion() + "." + name;
        return getClass(className);
    }

    private static Class<?> getUtilClass(String name) {
        try {
            return Class.forName(name); //Try before 1.8 first
        } catch (ClassNotFoundException ex) {
            try {
                return Class.forName("net.minecraft.util." + name); //Not 1.8
            } catch (ClassNotFoundException ex2) {
                return null;
            }
        }
    }

    private static String getVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    private static Object getHandle(Object wrapper) {
        Method getHandle = makeMethod(wrapper.getClass(), "getHandle");
        return callMethod(getHandle, wrapper);
    }

    //Utils
    private static Method makeMethod(Class<?> clazz, String methodName, Class<?>... paramaters) {
        try {
            return clazz.getDeclaredMethod(methodName, paramaters);
        } catch (NoSuchMethodException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> T callMethod(Method method, Object instance, Object... paramaters) {
        if (method == null) throw new RuntimeException("No such method");
        method.setAccessible(true);
        try {
            return (T) method.invoke(instance, paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> Constructor<T> makeConstructor(Class<?> clazz, Class<?>... paramaterTypes) {
        try {
            return (Constructor<T>) clazz.getConstructor(paramaterTypes);
        } catch (NoSuchMethodException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> T callConstructor(Constructor<T> constructor, Object... paramaters) {
        if (constructor == null) throw new RuntimeException("No such constructor");
        constructor.setAccessible(true);
        try {
            return (T) constructor.newInstance(paramaters);
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex.getCause());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Field makeField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static <T> T getField(Field field, Object instance) {
        if (field == null) throw new RuntimeException("No such field");
        field.setAccessible(true);
        try {
            return (T) field.get(instance);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}