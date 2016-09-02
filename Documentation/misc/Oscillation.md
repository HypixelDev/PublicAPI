### Oscillation
When storing temporary (weekly/monthly) stats, we store them in an oscillating (a/b) scheme in order to cleanly reset them every other week or month (depending on the stat).

In order to caculate the current oscillation name, take the provided start time (changes by the type of oscillation) and compare it to the current epoch value. Even output should be mapped to "a" and odd output should be mapped to "b".

#### Monthly Oscillation
Monthly tracking was started at 1417410000000 (Mon, 01 Dec 2014 05:00:00 GMT.)

```java
public static String getMonthlyOscillation() {
    Calendar startCalendar = new GregorianCalendar();
    startCalendar.setTime(new Date());

    Calendar endCalendar = new GregorianCalendar();
    endCalendar.setTime(new Date(1417410000000));

    int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
    int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

    return diffMonth % 2 == 0 ? "a" : "b";
}
```

#### Weekly Oscillation
Weekly tracking was started at 1417237200000 (Sat, 29 Nov 2014 05:00:00 GMT.)

```java
public static String getWeeklyOscillation() {
    long delta = Math.abs(System.currentTimeMillis() - 1417237200000L);
    long oscillation = delta / 604800000L;

    return oscillation % 2 == 0 ? "a" : "b";
}
```