package legendarena.api.utils;

public enum Months {

    JANUARY(1),
    FEBURARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private int month = -1;

    private Months(int month) {
        this.month = month;
    }

    public int getMonthId() {
        return month;
    }

}
