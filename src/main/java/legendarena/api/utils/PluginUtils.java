package legendarena.api.utils;import java.util.Random;/** * Various plugin utilities. Mainly obsolted now except for the chars set. * * @author ThePixelDev */public class PluginUtils {    public static final char[] chars = {            '♫',            '»',            '♦',            '•',            '■',            '™',            ' ',            '↔',            '‼',            '♥',            '↨',            '►',            '→',            ' ',            '‼',            ' ',			' '    };    public static boolean random(int roll, int checkFor) {        return new Random().nextInt(roll) == checkFor;    }}