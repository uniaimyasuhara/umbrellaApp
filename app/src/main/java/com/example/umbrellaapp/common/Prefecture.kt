package com.example.umbrellaapp.common

enum class Prefecture(val jp: String, val cities: List<Pair<String, String>>) {
    HOKKAIDO("北海道", listOf(
        "Sapporo" to "札幌",
        "Hakodate" to "函館",
        "Asahikawa" to "旭川"
    )),
    AOMORI("青森", listOf(
        "Aomori" to "青森市",
        "Hirosaki" to "弘前市",
        "Misawa" to "三沢市"
    )),
    IWATE("岩手", listOf(
        "Morioka" to "盛岡市",
        "Hanamaki" to "花巻市",
        "Kitakami" to "北上市"
    )),
    MIYAGI("宮城", listOf(
        "Sendai" to "仙台市",
        "Matsushima" to "松島市",
        "Ishinomaki" to "石巻市"
    )),
    AKITA("秋田", listOf(
        "Akita" to "秋田市",
        "Yokote" to "横手市",
        "Odate" to "大館市"
    )),
    YAMAGATA("山形", listOf(
        "Yamagata" to "山形市",
        "Sakata" to "酒田市",
        "Tsuruoka" to "鶴岡市"
    )),
    FUKUSHIMA("福島", listOf(
        "Fukushima" to "福島市",
        "Aizuwakamatsu" to "会津若松市",
        "Koriyama" to "郡山市"
    )),
    IBARAKI("茨城", listOf(
        "Mito" to "水戸市",
        "Tsukuba" to "つくば市",
        "Hitachi" to "日立市"
    )),
    TOCHIGI("栃木", listOf(
        "Utsunomiya" to "宇都宮市",
        "Ashikaga" to "足利市",
        "Kanuma" to "鹿沼市"
    )),
    GUNMA("群馬", listOf(
        "Maebashi" to "前橋市",
        "Takasaki" to "高崎市",
        "Ota" to "太田市"
    )),
    SAITAMA("埼玉", listOf(
        "Saitama" to "さいたま市",
        "Kawaguchi" to "川口市",
        "Kawagoe" to "川越市"
    )),
    CHIBA("千葉", listOf(
        "Chiba" to "千葉市",
        "Funabashi" to "船橋市",
        "Ichikawa" to "市川市"
    )),
    TOKYO("東京", listOf(
        "Tokyo" to "東京市",
        "Shinjuku" to "新宿区",
        "Shibuya" to "渋谷区"
    )),
    KANAGAWA("神奈川", listOf(
        "Yokohama" to "横浜市",
        "Kawasaki" to "川崎市",
        "Sagamihara" to "相模原市"
    )),
    NIIGATA("新潟", listOf(
        "Niigata" to "新潟市",
        "Nagaoka" to "長岡市",
        "Joetsu" to "上越市"
    )),
    TOYAMA("富山", listOf(
        "Toyama" to "富山市",
        "Takaoka" to "高岡市",
        "Uozu" to "魚津市"
    )),
    ISHIKAWA("石川", listOf(
        "Kanazawa" to "金沢市",
        "Komatsu" to "小松市",
        "Nanao" to "七尾市"
    )),
    FUKUI("福井", listOf(
        "Fukui" to "福井市",
        "Obama" to "小浜市",
        "Tsuruga" to "敦賀市"
    )),
    YAMANASHI("山梨", listOf(
        "Kofu" to "甲府市",
        "Fuefuki" to "笛吹市",
        "Fujiyoshida" to "富士吉田市"
    )),
    NAGANO("長野", listOf(
        "Nagano" to "長野市",
        "Matsumoto" to "松本市",
        "Ueda" to "上田市"
    )),
    GIFU("岐阜", listOf(
        "Gifu" to "岐阜市",
        "Takayama" to "高山市",
        "Hashima" to "羽島市"
    )),
    SHIZUOKA("静岡", listOf(
        "Shizuoka" to "静岡市",
        "Hamamatsu" to "浜松市",
        "Numazu" to "沼津市"
    )),
    AICHI("愛知", listOf(
        "Nagoya" to "名古屋市",
        "Toyohashi" to "豊橋市",
        "Okazaki" to "岡崎市"
    )),
    MIE("三重", listOf(
        "Tsu" to "津市",
        "Yokkaichi" to "四日市市",
        "Suzuka" to "鈴鹿市"
    )),
    SHIGA("滋賀", listOf(
        "Otsu" to "大津市",
        "Hikone" to "彦根市",
        "Nagahama" to "長浜市"
    )),
    KYOTO("京都", listOf(
        "Kyoto" to "京都市",
        "Uji" to "宇治市",
        "Miyazu" to "宮津市"
    )),
    OSAKA("大阪", listOf(
        "Osaka" to "大阪市",
        "Sakai" to "堺市",
        "Higashi-Osaka" to "東大阪市"
    )),
    HYOGO("兵庫", listOf(
        "Kobe" to "神戸市",
        "Himeji" to "姫路市",
        "Nishinomiya" to "西宮市"
    )),
    NARA("奈良", listOf(
        "Nara" to "奈良市",
        "Yamatokoriyama" to "大和郡山市",
        "Kashihara" to "橿原市"
    )),
    WAKAYAMA("和歌山", listOf(
        "Wakayama" to "和歌山市",
        "Hashimoto" to "橋本市",
        "Wakayama" to "和歌山市"
    )),
    TOTTORI("鳥取", listOf(
        "Tottori" to "鳥取市",
        "Yonago" to "米子市",
        "Kurayoshi" to "倉吉市"
    )),
    SHIMANE("島根", listOf(
        "Matsue" to "松江市",
        "Hamada" to "浜田市",
        "Izumo" to "出雲市"
    )),
    OKAYAMA("岡山", listOf(
        "Okayama" to "岡山市",
        "Kurashiki" to "倉敷市",
        "Tsuyama" to "津山市"
    )),
    HIROSHIMA("広島", listOf(
        "Hiroshima" to "広島市",
        "Fukuyama" to "福山市",
        "Kure" to "呉市"
    )),
    YAMAGUCHI("山口", listOf(
        "Shimonoseki" to "下関市",
        "Ube" to "宇部市",
        "Yamaguchi" to "山口市"
    )),
    TOKUSHIMA("徳島", listOf(
        "Tokushima" to "徳島市",
        "Naruto" to "鳴門市",
        "Komatsushima" to "小松島市"
    )),
    KAGAWA("香川", listOf(
        "Takamatsu" to "高松市",
        "Marugame" to "丸亀市",
        "Sakaide" to "坂出市"
    )),
    EHIME("愛媛", listOf(
        "Matsuyama" to "松山市",
        "Niihama" to "新居浜市",
        "Imabari" to "今治市"
    )),
    KOCHI("高知", listOf(
        "Kochi" to "高知市",
        "Muroto" to "室戸市",
        "Aki" to "安芸市"
    )),
    FUKUOKA("福岡", listOf(
        "Fukuoka" to "福岡市",
        "Kitakyushu" to "北九州市",
        "Kurume" to "久留米市"
    )),
    SAGA("佐賀", listOf(
        "Saga" to "佐賀市",
        "Karatsu" to "唐津市",
        "Tosu" to "鳥栖市"
    )),
    NAGASAKI("長崎", listOf(
        "Nagasaki" to "長崎市",
        "Sasebo" to "佐世保市",
        "Isahaya" to "諫早市"
    )),
    KUMAMOTO("熊本", listOf(
        "Kumamoto" to "熊本市",
        "Yatsushiro" to "八代市",
        "Hitoyoshi" to "人吉市"
    )),
    OITA("大分", listOf(
        "Oita" to "大分市",
        "Beppu" to "別府市",
        "Nakatsu" to "中津市"
    )),
    MIYAZAKI("宮崎", listOf(
        "Miyazaki" to "宮崎市",
        "Miyakonojo" to "都城市",
        "Nobeoka" to "延岡市"
    )),
    KAGOSHIMA("鹿児島", listOf(
        "Kagoshima" to "鹿児島市",
        "Kanoya" to "鹿屋市",
        "Shibushi" to "志布志市"
    )),
    OKINAWA("沖縄", listOf(
        "Naha" to "那覇市",
        "Ginowan" to "宜野湾市",
        "Ishigaki" to "石垣市"
    ))
}

enum class Locate(lat:String, lon:String){


}