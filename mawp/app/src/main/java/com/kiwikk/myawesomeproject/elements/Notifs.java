package com.kiwikk.myawesomeproject.elements;

import java.util.Random;

public class Notifs {
    //9
    static Random rnd = new Random();

    static String[] from0_to10 = new String[]{"Элис Тэн-Робертс уже к полутора годам знала все столицы мира, а чего добился ты?"};
    static String[] from10_to20 = new String[]{"Брайан Циммерман уже в 11 лет стал мэром, представляешь??",
            "Луи Брайль в 13 лет придумал самый удобный шрифт для слепых, которым пользуются по сей день",
            "Не каждый студент-второкурсник может похвастаться, что им интересовались Facebook и Google. Обе компании предложили Бену Пастернаку (в 16-то лет) у них работать, после того, как его игра Impossible Rush набрала на App Store 500 000 закачек за шесть недель после релиза."};
    static String[] from20_to30 = new String[]{"В 22 года Уолт Дисней создал небольшую анимационную студию (угадай какую)",
            "В 23 года Исаак Ньютон открыл Закон всемирного тяготения, а ты только пачки чипсов открываешь",
            "В 26 лет Марк Цукерберг был признан одним из самых молодых миллиардеров в мире"};
    static String[] from30_to40 = new String[]{"В 32 Джоан Роулинг опубликовала свою первую книгу о Гарри Поттере с начальным тиражом в 1к экземпляров",
            "В 35 лет Уильям Боинг создал свой первый самолёт и поднял его в воздух :)",
            "В 39 лет Вера Вонг, 17 лет проработавшая редактором в журнале Vogue, начала создавать свадебные платья, которые сейчас знамениты на весь мир."};
    static String[] from40_to50 = new String[]{"В 41 год Христофор Колумб открыл Америку",
            "В 44 года Сэм Уолтон открыл первый магазин Walmart и стал одним из самых богатых людей в мире. До этого все его магазины терпели крах",
            "В 49 лет немецкий сапожник Адольф Дасслер основал Adidas"};
    static String[] from50_to60 = new String[]{"В 54 года актёр Лесли Нильсен открыл в себе комедийный талант, благодаря которому прославился на весь мир",
            "В 58 лет Мигель де Сервантес опубликовал \"Дон Кихота\". До этого он искал работу, способную его прокормить"};
    static String[] from60_to70 = new String[]{"В 68 лет канадский педагого, драматург и писатель Робертсон Дэвис начал писать, после того как вышел на пенсию. " +
            "Он опубликовал роман \"Мятежные ангелы\" и был номинирован на Нобелевскую премию"};
    static String[] from70_to80 = new String[]{"Да думаю можно и отдохнуть"};
    static String[] motivation = new String[]{"А часики-то тикают",
            "А вот Ленка из пятого подъезда..",
            "Ну давай ещё посидим, подождём, мы же никуда не торопимся",
            "И помни: успех в трудностях", "Жизнь идёт, а ты нет", "Тебе не стыдно?",
            "Начать никогда не поздно, поздно не начать сейчас", "Предлагаю не сдаваться"};

    public static String getFrom0_to10() {
        if (rnd.nextBoolean()) return motivation[rnd.nextInt(motivation.length)];
        else return from0_to10[rnd.nextInt(from0_to10.length)];
    }

    public static String getFrom10_to20() {
        if (rnd.nextBoolean()) return from10_to20[rnd.nextInt(from10_to20.length)];
        else return getFrom0_to10();
    }

    public static String getFrom20_to30() {
        if (rnd.nextBoolean()) return from20_to30[rnd.nextInt(from20_to30.length)];
        else return getFrom10_to20();
    }

    public static String getFrom30_to40() {
        if (rnd.nextBoolean()) return from30_to40[rnd.nextInt(from30_to40.length)];
        else return getFrom20_to30();
    }

    public static String getFrom40_to50() {
        if (rnd.nextBoolean()) return from40_to50[rnd.nextInt(from40_to50.length)];
        else return getFrom30_to40();
    }

    public static String getFrom50_to60() {
        if (rnd.nextBoolean()) return from50_to60[rnd.nextInt(from50_to60.length)];
        else return getFrom40_to50();
    }

    public static String getFrom60_to70() {
        if (rnd.nextBoolean()) return from60_to70[rnd.nextInt(from60_to70.length)];
        else return getFrom50_to60();
    }

    public static String getFrom70_to80() {
        if (rnd.nextBoolean()) return from70_to80[rnd.nextInt(from70_to80.length)];
        else return getFrom60_to70();
    }

    public static String getMotiv(int age) {
        if (age >= 70) return getFrom70_to80();
        else if (age >= 60) return getFrom60_to70();
        else if (age >= 50) return getFrom50_to60();
        else if (age >= 40) return getFrom40_to50();
        else if (age >= 30) return getFrom30_to40();
        else if (age >= 20) return getFrom20_to30();
        else if (age >= 10) return getFrom10_to20();
        else return getFrom0_to10();
    }
}
