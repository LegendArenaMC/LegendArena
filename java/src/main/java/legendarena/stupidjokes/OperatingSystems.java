package legendarena.stupidjokes;

/**
 * Stupid operating system jokes. It has no use. Seriously. No use whatsoever. I'm not kidding.<br><br>
 *
 * Now shoo.<br><br>
 *
 * Why are you still here? Shoo. Seriously. Shoo.
 *
 * @author ThePixelDev
 */
public enum OperatingSystems {

    UBUNTU(OSSuperGroup.LINUX),
    FEDORA(OSSuperGroup.LINUX),
    LINUXMINT(OSSuperGroup.LINUX),
    WINDOWS(OSSuperGroup.WINDOWS), //ew windows
    OSX(OSSuperGroup.MAC),
    ARCH(OSSuperGroup.LINUX),
    CRUNCHBANG(OSSuperGroup.LINUX),
    ELEMENTARY(OSSuperGroup.LINUX);

    private OSSuperGroup upper;

    OperatingSystems(OSSuperGroup upper) {
        this.upper = upper;
    }

    public OSSuperGroup getUpperOS() {
        return this.upper;
    }

    public boolean isLinux() {
        return this.upper.equals(OSSuperGroup.LINUX);
    }

    public enum OSSuperGroup {

        LINUX,
        WINDOWS, //insert obligatory winfail comment here
        MAC

    }

}
