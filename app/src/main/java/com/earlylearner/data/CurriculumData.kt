package com.earlylearner.data

import com.earlylearner.R
import com.earlylearner.data.model.LearningCategory
import com.earlylearner.data.model.LearningItem
import com.earlylearner.data.model.StrokePath
import com.earlylearner.data.model.StrokePoint

object CurriculumData {

    // 1. HINDI SWAR (स्वर - 13)
    val hindiSwar: List<LearningItem> = listOf(
        LearningItem(
            id = "swar_1",
            category = LearningCategory.HINDI_SWAR,
            symbol = "अ",
            titleHindi = "अ से अनार",
            titleEnglish = "A for Anar (Pomegranate)",
            wordHindi = "अनार",
            wordEnglish = "Pomegranate",
            phonicsSound = "अ (uh)",
            pronunciationHindi = "अ से अनार। अनार मीठा और लाल होता है।",
            pronunciationEnglish = "A for Anar. Pomegranate is red and healthy.",
            objectEmoji = "🍎",
            funFact = "अनार के लाल दाने सेहत के लिए बहुत अच्छे होते हैं!",
            tracingPaths = listOf(
                StrokePath(listOf(StrokePoint(0.35f, 0.25f), StrokePoint(0.48f, 0.32f), StrokePoint(0.38f, 0.45f)), "पहला मोड़"),
                StrokePath(listOf(StrokePoint(0.38f, 0.45f), StrokePoint(0.50f, 0.58f), StrokePoint(0.35f, 0.70f)), "दूसरा मोड़"),
                StrokePath(listOf(StrokePoint(0.42f, 0.47f), StrokePoint(0.65f, 0.47f)), "मध्य रेखा"),
                StrokePath(listOf(StrokePoint(0.65f, 0.25f), StrokePoint(0.65f, 0.75f)), "खड़ी रेखा"),
                StrokePath(listOf(StrokePoint(0.55f, 0.25f), StrokePoint(0.75f, 0.25f)), "शिरोरेखा")
            )
        ),
        LearningItem(
            id = "swar_2",
            category = LearningCategory.HINDI_SWAR,
            symbol = "आ",
            titleHindi = "आ से आम",
            titleEnglish = "Aa for Aam (Mango)",
            wordHindi = "आम",
            wordEnglish = "Mango",
            phonicsSound = "आ (aa)",
            pronunciationHindi = "आ से आम। आम फलों का राजा है।",
            pronunciationEnglish = "Aa for Aam. Mango is the king of fruits.",
            objectEmoji = "🥭",
            funFact = "आम भारत का राष्ट्रीय फल है और बहुत मीठा होता है!",
            tracingPaths = listOf(
                StrokePath(listOf(StrokePoint(0.30f, 0.25f), StrokePoint(0.42f, 0.32f), StrokePoint(0.32f, 0.45f))),
                StrokePath(listOf(StrokePoint(0.32f, 0.45f), StrokePoint(0.45f, 0.58f), StrokePoint(0.30f, 0.70f))),
                StrokePath(listOf(StrokePoint(0.35f, 0.47f), StrokePoint(0.55f, 0.47f))),
                StrokePath(listOf(StrokePoint(0.55f, 0.25f), StrokePoint(0.55f, 0.75f))),
                StrokePath(listOf(StrokePoint(0.70f, 0.25f), StrokePoint(0.70f, 0.75f))),
                StrokePath(listOf(StrokePoint(0.48f, 0.25f), StrokePoint(0.78f, 0.25f)))
            )
        ),
        LearningItem(
            id = "swar_3",
            category = LearningCategory.HINDI_SWAR,
            symbol = "इ",
            titleHindi = "इ से इमली",
            titleEnglish = "I for Imli (Tamarind)",
            wordHindi = "इमली",
            wordEnglish = "Tamarind",
            phonicsSound = "इ (i)",
            pronunciationHindi = "इ से इमली। इमली खट्टी-मीठी होती है।",
            pronunciationEnglish = "I for Imli. Tamarind is tangy and sweet.",
            objectEmoji = "🫒",
            funFact = "इमली से खट्टी-मीठी चटनी बनती है!"
        ),
        LearningItem(
            id = "swar_4",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ई",
            titleHindi = "ई से ईख",
            titleEnglish = "Ee for Eekh (Sugarcane)",
            wordHindi = "ईख",
            wordEnglish = "Sugarcane",
            phonicsSound = "ई (ee)",
            pronunciationHindi = "ई से ईख। ईख से मीठा गुड़ और चीनी बनती है।",
            pronunciationEnglish = "Ee for Eekh. Sugarcane gives sweet juice.",
            objectEmoji = "🎋",
            funFact = "ईख का रस पीने से बहुत ऊर्जा मिलती है!"
        ),
        LearningItem(
            id = "swar_5",
            category = LearningCategory.HINDI_SWAR,
            symbol = "उ",
            titleHindi = "उ से उल्लू",
            titleEnglish = "U for Ullu (Owl)",
            wordHindi = "उल्लू",
            wordEnglish = "Owl",
            phonicsSound = "उ (u)",
            pronunciationHindi = "उ से उल्लू। उल्लू रात को जागता है।",
            pronunciationEnglish = "U for Ullu. Owl sees clearly in the dark.",
            objectEmoji = "🦉",
            funFact = "उल्लू अपनी गर्दन को लगभग पूरा गोल घुमा सकता है!",
            isAnimalOrBird = true
        ),
        LearningItem(
            id = "swar_6",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ऊ",
            titleHindi = "ऊ से ऊन",
            titleEnglish = "Oo for Oon (Wool)",
            wordHindi = "ऊन",
            wordEnglish = "Wool",
            phonicsSound = "ऊ (oo)",
            pronunciationHindi = "ऊ से ऊन। ऊन से गरम स्वेटर बनते हैं।",
            pronunciationEnglish = "Oo for Oon. Wool makes warm winter clothes.",
            objectEmoji = "🧶",
            funFact = "ऊन हमें भेड़ (sheep) से मिलती है!"
        ),
        LearningItem(
            id = "swar_7",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ऋ",
            titleHindi = "ऋ से ऋषि",
            titleEnglish = "Ri for Rishi (Sage)",
            wordHindi = "ऋषि",
            wordEnglish = "Sage / Saint",
            phonicsSound = "ऋ (ri)",
            pronunciationHindi = "ऋ से ऋषि। ऋषि वन में तप और विद्या सीखते हैं।",
            pronunciationEnglish = "Ri for Rishi. Sage shares wisdom and peace.",
            objectEmoji = "🧘",
            funFact = "ऋषि सदा शांत रहकर ज्ञान बाँटते हैं।"
        ),
        LearningItem(
            id = "swar_8",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ए",
            titleHindi = "ए से एड़ी",
            titleEnglish = "E for Edi (Heel)",
            wordHindi = "एड़ी",
            wordEnglish = "Heel",
            phonicsSound = "ए (e)",
            pronunciationHindi = "ए से एड़ी। एड़ी पैर का भाग है।",
            pronunciationEnglish = "E for Edi. Heel supports our feet.",
            objectEmoji = "🦶",
            funFact = "एड़ी हमें चलने और दौड़ने में मदद करती है!"
        ),
        LearningItem(
            id = "swar_9",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ऐ",
            titleHindi = "ऐ से ऐनक",
            titleEnglish = "Ai for Ainak (Glasses)",
            wordHindi = "ऐनक",
            wordEnglish = "Spectacles",
            phonicsSound = "ऐ (ai)",
            pronunciationHindi = "ऐ से ऐनक। ऐनक आँखों पर लगाते हैं।",
            pronunciationEnglish = "Ai for Ainak. Glasses help us see clearly.",
            objectEmoji = "👓",
            funFact = "ऐनक हमें साफ-साफ पढ़ने में मदद करती है!"
        ),
        LearningItem(
            id = "swar_10",
            category = LearningCategory.HINDI_SWAR,
            symbol = "ओ",
            titleHindi = "ओ से ओखली",
            titleEnglish = "O for Okhli (Mortar)",
            wordHindi = "ओखली",
            wordEnglish = "Mortar",
            phonicsSound = "ओ (o)",
            pronunciationHindi = "ओ से ओखली। ओखली में दाने कूटे जाते हैं।",
            pronunciationEnglish = "O for Okhli. Mortar grinds herbs and grains.",
            objectEmoji = "🥣",
            funFact = "ओखली में मसाले और अनाज पीसे जाते हैं!"
        ),
        LearningItem(
            id = "swar_11",
            category = LearningCategory.HINDI_SWAR,
            symbol = "औ",
            titleHindi = "औ से औरत",
            titleEnglish = "Au for Aurat (Woman)",
            wordHindi = "औरत / औजार",
            wordEnglish = "Woman / Tool",
            phonicsSound = "औ (au)",
            pronunciationHindi = "औ से औरत और औ से औजार।",
            pronunciationEnglish = "Au for Aurat. Kindness and learning.",
            objectEmoji = "👩",
            funFact = "माँ हमें सबसे पहले बोलना सिखाती है!"
        ),
        LearningItem(
            id = "swar_12",
            category = LearningCategory.HINDI_SWAR,
            symbol = "अं",
            titleHindi = "अं से अंगूर",
            titleEnglish = "Ang for Angoor (Grapes)",
            wordHindi = "अंगूर",
            wordEnglish = "Grapes",
            phonicsSound = "अं (ang)",
            pronunciationHindi = "अं से अंगूर। अंगूर गुच्छों में फलते हैं।",
            pronunciationEnglish = "Ang for Angoor. Grapes grow in sweet bunches.",
            objectEmoji = "🍇",
            funFact = "अंगूर हरे और काले दोनों रंगों के होते हैं!"
        ),
        LearningItem(
            id = "swar_13",
            category = LearningCategory.HINDI_SWAR,
            symbol = "अः",
            titleHindi = "अः से खाली",
            titleEnglish = "Aha (Empty / Cheerful)",
            wordHindi = "खाली (अः)",
            wordEnglish = "End Vowel",
            phonicsSound = "अः (ah)",
            pronunciationHindi = "अः से खाली! बजाओ ताली!",
            pronunciationEnglish = "Aha, clap your hands with joy!",
            objectEmoji = "👏",
            funFact = "अः वर्णमाला के स्वर पूरे होने पर हम ताली बजाते हैं!"
        )
    )

    // 2. HINDI VYANJAN (व्यंजन - 36)
    val hindiVyanjan: List<LearningItem> = listOf(
        LearningItem("vyan_1", LearningCategory.HINDI_VYANJAN, "क", null, "क से कबूतर", "K for Kabutar (Pigeon)", "कबूतर", "Pigeon", "क (ka)", "क से कबूतर। कबूतर गुटर गूँ करता है।", "K for Kabutar. Pigeon coos softly in the breeze.", "🕊️", "कबूतर शांति और प्रेम का प्रतीक है!", null, emptyList(), R.drawable.img_pigeon_kabutar, true),
        LearningItem("vyan_2", LearningCategory.HINDI_VYANJAN, "ख", null, "ख से खरगोश", "Kh for Khargosh (Rabbit)", "खरगोश", "Rabbit", "ख (kha)", "ख से खरगोश। खरगोश गाजर खाता है।", "Kh for Khargosh. Rabbit loves fresh carrots.", "🐰", "खरगोश बहुत तेज़ी से दौड़ता है!", null, emptyList(), R.drawable.img_rabbit_animal, true),
        LearningItem("vyan_3", LearningCategory.HINDI_VYANJAN, "ग", null, "ग से गमला", "G for Gamla (Flowerpot)", "गमला", "Flower Pot", "ग (ga)", "ग से गमला। गमले में सुंदर पौधे लगाते हैं।", "G for Gamla. Pot holds beautiful plants.", "🪴", "गमले में रोज़ पानी देने से फूल खिलते हैं!"),
        LearningItem("vyan_4", LearningCategory.HINDI_VYANJAN, "घ", null, "घ से घर / घड़ी", "Gh for Ghar (House / Clock)", "घर", "House", "घ (gha)", "घ से घर। घर में हम सब प्यार से रहते हैं।", "Gh for Ghar. Home is where family lives.", "🏠", "घ से घड़ी भी होती है जो समय बताती है!"),
        LearningItem("vyan_5", LearningCategory.HINDI_VYANJAN, "ङ", null, "ङ से खाली", "Nga (Nasal sound)", "खाली", "Nasal Sound", "ङ (nga)", "ङ से खाली।", "Nga sound.", "✨", "ङ पंचम अक्षर है।"),
        LearningItem("vyan_6", LearningCategory.HINDI_VYANJAN, "च", null, "च से चम्मच", "Ch for Chammach (Spoon)", "चम्मच", "Spoon", "च (cha)", "च से चम्मच। चम्मच से खाना खाते हैं।", "Ch for Chammach. Spoon helps us eat tasty food.", "🥄", "च से चाँद भी होता है जो रात को चमकता है!"),
        LearningItem("vyan_7", LearningCategory.HINDI_VYANJAN, "छ", null, "छ से छतरी", "Chh for Chhatri (Umbrella)", "छतरी", "Umbrella", "छ (chha)", "छ से छतरी। बारिश और धूप से बचाती है।", "Chh for Chhatri. Umbrella keeps us dry in rain.", "☂️", "छतरी रंग-बिरंगी होती है!"),
        LearningItem("vyan_8", LearningCategory.HINDI_VYANJAN, "ज", null, "ज से जहाज / जग", "J for Jahaz (Ship / Water Jug)", "जहाज", "Ship", "ज (ja)", "ज से जहाज। जहाज पानी पर तैरता है।", "J for Jahaz. Ship sails on big oceans.", "🚢", "ज से जल भी होता है जिसे हम पीते हैं!"),
        LearningItem("vyan_9", LearningCategory.HINDI_VYANJAN, "झ", null, "झ से झंडा", "Jh for Jhanda (Flag)", "झंडा", "Flag", "झ (jha)", "झ से झंडा। तिरंगा हमारा प्यारा झंडा है।", "Jh for Jhanda. Flag waves proudly high.", "🇮🇳", "झ से झरना भी होता है जिससे मीठा पानी गिरता है!"),
        LearningItem("vyan_10", LearningCategory.HINDI_VYANJAN, "ञ", null, "ञ से खाली", "Nya (Nasal sound)", "खाली", "Nasal sound", "ञ (nya)", "ञ से खाली।", "Nya sound.", "🌟", "ञ पंचमाक्षर वर्ण है।"),
        LearningItem("vyan_11", LearningCategory.HINDI_VYANJAN, "ट", null, "ट से टमाटर", "T for Tamatar (Tomato)", "टमाटर", "Tomato", "ट (ta)", "ट से टमाटर। टमाटर लाल-लाल और रसीला होता है।", "T for Tamatar. Tomato is red and juicy.", "🍅", "टमाटर खाने से गाल लाल-लाल होते हैं!"),
        LearningItem("vyan_12", LearningCategory.HINDI_VYANJAN, "ठ", null, "ठ से ठठेरा", "Th for Thathera (Metalsmith)", "ठठेरा", "Metalsmith", "ठ (tha)", "ठ से ठठेरा। बर्तन बनाने वाला।", "Th for Thathera. Crafts beautiful metal pots.", "🪣", "ठठेरा धातु के बर्तन बनाता है!"),
        LearningItem("vyan_13", LearningCategory.HINDI_VYANJAN, "ड", null, "ड से डमरू", "D for Damroo (Drum)", "डमरू", "Small Drum", "ड (da)", "ड से डमरू। डमरू बाजे डम-डम-डम।", "D for Damroo. Drum makes cheerful beats.", "🪘", "ड से डाल भी होती है जिस पर पक्षी बैठते हैं!"),
        LearningItem("vyan_14", LearningCategory.HINDI_VYANJAN, "ढ", null, "ढ से ढक्कन / ढोलक", "Dh for Dhakkan (Lid / Drum)", "ढक्कन", "Lid", "ढ (dha)", "ढ से ढक्कन। ढक्कन बर्तन को ढकता है।", "Dh for Dhakkan. Lid keeps food clean and warm.", "🍲", "ढ से ढोलक भी होती है जो शादी में बजती है!"),
        LearningItem("vyan_15", LearningCategory.HINDI_VYANJAN, "ण", null, "ण से बाण", "Na (Retroflex)", "बाण", "Arrow", "ण (na)", "ण से बाण।", "Na letter.", "🏹", "ण शब्द के मध्य या अंत में आता है!"),
        LearningItem("vyan_16", LearningCategory.HINDI_VYANJAN, "त", null, "त से तरबूज", "T for Tarbooz (Watermelon)", "तरबूज", "Watermelon", "त (ta)", "त से तरबूज। गर्मियों में ठंडक देता है।", "T for Tarbooz. Watermelon is refreshing in summer.", "🍉", "तरबूज अंदर से लाल और मीठा होता है!"),
        LearningItem("vyan_17", LearningCategory.HINDI_VYANJAN, "थ", null, "थ से थर्मस", "Th for Thermos", "थर्मस", "Thermos Flask", "थ (tha)", "थ से थर्मस। पानी गरम या ठंडा रखता है।", "Th for Thermos. Keeps water cold or warm.", "🍶", "थ से थाली भी होती है जिसमें हम खाना खाते हैं!"),
        LearningItem("vyan_18", LearningCategory.HINDI_VYANJAN, "द", null, "द से दवात / दिया", "D for Dawaat / Diya", "दीपक", "Oil Lamp / Inkpot", "द (da)", "द से दिया। दिया रोशनी फैलाता है।", "D for Diya. Lamp spreads bright light.", "🪔", "द से दूध भी होता है जो हमें ताकत देता है!"),
        LearningItem("vyan_19", LearningCategory.HINDI_VYANJAN, "ध", null, "ध से धनुष", "Dh for Dhanush (Bow)", "धनुष", "Bow", "ध (dha)", "ध से धनुष। धनुष और तीर।", "Dh for Dhanush. Bow and arrow.", "🏹", "ध से धरती भी होती है जिस पर हम रहते हैं!"),
        LearningItem("vyan_20", LearningCategory.HINDI_VYANJAN, "न", null, "न से नल", "N for Nal (Tap)", "नल", "Water Tap", "न (na)", "न से नल। नल से पानी मिलता है।", "N for Nal. Tap gives clean drinking water.", "🚰", "पानी की हर बूँद बचानी चाहिए!"),
        LearningItem("vyan_21", LearningCategory.HINDI_VYANJAN, "प", null, "प से पतंग", "P for Patang (Kite)", "पतंग", "Kite", "प (pa)", "प से पतंग। पतंग आसमान में उड़ती है।", "P for Patang. Kite flies high in the blue sky.", "🪁", "पतंग हवा में लहराती है!"),
        LearningItem("vyan_22", LearningCategory.HINDI_VYANJAN, "फ", null, "फ से फल", "Ph for Phal (Fruits)", "फल", "Fruits", "फ (pha)", "फ से फल। ताजे फल खाओ, तंदुरुस्त रहो।", "Ph for Phal. Fresh fruits keep us strong.", "🍎", "सेब, केला, संतरा सब फल हैं!"),
        LearningItem("vyan_23", LearningCategory.HINDI_VYANJAN, "ब", null, "ब से बत्तख / बस", "B for Battakh (Duck / Bus)", "बत्तख", "Duck", "ब (ba)", "ब से बत्तख। बत्तख पानी में तैरती है।", "B for Battakh. Duck swims happily.", "🦆", "ब से बस भी होती है जो स्कूल ले जाती है!", null, emptyList(), null, true),
        LearningItem("vyan_24", LearningCategory.HINDI_VYANJAN, "भ", null, "भ से भालू", "Bh for Bhalu (Bear)", "भालू", "Bear", "भ (bha)", "भ से भालू। भालू को शहद बहुत पसंद है।", "Bh for Bhalu. Bear loves sweet honey.", "🐻", "भालू घने जंगलों में रहता है!", null, emptyList(), R.drawable.img_bear_bhalu, true),
        LearningItem("vyan_25", LearningCategory.HINDI_VYANJAN, "म", null, "म से मोर", "M for Mor (Peacock)", "मोर", "Peacock", "म (ma)", "म से मोर। मोर भारत का राष्ट्रीय पक्षी है।", "M for Mor. Peacock is the national bird.", "🦚", "मोर के पंख बहुत सुंदर और रंग-बिरंगे होते हैं!", null, emptyList(), R.drawable.img_peacock_bird, true),
        LearningItem("vyan_26", LearningCategory.HINDI_VYANJAN, "य", null, "य से यज्ञ", "Y for Yagya (Sacred Fire)", "यज्ञ", "Sacred Fire", "य (ya)", "य से यज्ञ। वातावरण शुद्ध करता है।", "Y for Yagya. Spreads pure fragrance.", "🔥", "य से योग भी होता है जिससे शरीर स्वस्थ रहता है!"),
        LearningItem("vyan_27", LearningCategory.HINDI_VYANJAN, "र", null, "र से रथ / रेल", "R for Rath (Chariot / Train)", "रथ", "Chariot", "र (ra)", "र से रथ और र से रेलगाड़ी।", "R for Rath. Chariot and train.", "🚂", "रेलगाड़ी छुक-छुक करके चलती है!"),
        LearningItem("vyan_28", LearningCategory.HINDI_VYANJAN, "ल", null, "ल से लट्टू", "L for Lattoo (Top)", "लट्टू", "Spinning Top", "ल (la)", "ल से लट्टू। लट्टू गोल-गोल घूमता है।", "L for Lattoo. Top spins round and round.", "🪀", "ल से लोमड़ी भी होती है जो बहुत चतुर होती है!"),
        LearningItem("vyan_29", LearningCategory.HINDI_VYANJAN, "व", null, "व से वन / वक", "V for Van (Forest / Crane)", "वन", "Forest", "व (va)", "व से वन। वन में हरे-भरे पेड़ होते हैं।", "V for Van. Forest has green trees.", "🌲", "पेड़ हमें ताज़ी हवा और ऑक्सीजन देते हैं!"),
        LearningItem("vyan_30", LearningCategory.HINDI_VYANJAN, "श", null, "श से शेर", "Sh for Sher (Lion)", "शेर", "Lion", "श (sha)", "श से शेर। शेर जंगल का राजा कहलाता है।", "Sh for Sher. Lion is the king of jungle.", "🦁", "शेर जंगल का सबसे बलवान राजा है!", null, emptyList(), R.drawable.img_tiger_animal, true),
        LearningItem("vyan_31", LearningCategory.HINDI_VYANJAN, "ष", null, "ष से षट्कोण", "Sh for Shatkon (Hexagon)", "षट्कोण", "Hexagon", "ष (sha)", "ष से षट्कोण। छह कोनों वाली आकृति।", "Sh for Shatkon. Hexagon has six sides.", "⬡", "षट्कोण में 6 भुजाएं होती हैं!"),
        LearningItem("vyan_32", LearningCategory.HINDI_VYANJAN, "स", null, "स से सेब / सूरज", "S for Seb (Apple / Sun)", "सेब", "Apple", "स (sa)", "स से सेब और स से सूरज।", "S for Seb and Sun. Apple keeps doctor away.", "☀️", "सूरज सुबह रोशनी और गर्मी लेकर आता है!"),
        LearningItem("vyan_33", LearningCategory.HINDI_VYANJAN, "ह", null, "ह से हाथी", "H for Hathi (Elephant)", "हाथी", "Elephant", "ह (ha)", "ह से हाथी। हाथी की लंबी सूंड होती है।", "H for Hathi. Elephant is the biggest land animal.", "🐘", "हाथी सबसे बड़ा और समझदार जानवर है!", null, emptyList(), R.drawable.img_elephant_animal, true),
        LearningItem("vyan_34", LearningCategory.HINDI_VYANJAN, "क्ष", null, "क्ष से क्षत्रिय", "Ksh for Kshatriya (Protector)", "क्षत्रिय", "Warrior", "क्ष (ksha)", "क्ष से क्षत्रिय। देश की रक्षा करते हैं।", "Ksh for Kshatriya. Brave protectors.", "🛡️", "क्ष संयुक्त व्यंजन है (क् + ष्)।"),
        LearningItem("vyan_35", LearningCategory.HINDI_VYANJAN, "त्र", null, "त्र से त्रिशूल", "Tr for Trishul (Trident)", "त्रिशूल", "Trident", "त्र (tra)", "त्र से त्रिशूल। तीन फलकों वाला अस्त्र।", "Tr for Trishul. Three-pointed spear.", "🔱", "त्र संयुक्त व्यंजन है (त् + र्)।"),
        LearningItem("vyan_36", LearningCategory.HINDI_VYANJAN, "ज्ञ", null, "ज्ञ से ज्ञानी", "Gy for Gyani (Wise Scholar)", "ज्ञानी", "Wise Scholar", "ज्ञ (gya)", "ज्ञ से ज्ञानी। ज्ञानवान और विद्वान।", "Gy for Gyani. Wise person who loves learning.", "📚", "खूब पढ़ो और ज्ञानी बनो!")
    )

    // 3. ENGLISH ALPHABETS (A TO Z - 26)
    val englishAlphabets: List<LearningItem> = listOf(
        LearningItem("en_a", LearningCategory.ENGLISH_ALPHABETS, "A", "a", "A for Apple", "A for Apple", "Apple", "Apple", "æ (aah)", "A for Apple. Apple is sweet and red.", "A for Apple", "🍎", "An apple a day keeps the doctor away!"),
        LearningItem("en_b", LearningCategory.ENGLISH_ALPHABETS, "B", "b", "B for Ball", "B for Ball", "Ball", "Ball", "b (buh)", "B for Ball. Let's play with the ball.", "B for Ball", "⚽", "Balls can bounce high in the park!"),
        LearningItem("en_c", LearningCategory.ENGLISH_ALPHABETS, "C", "c", "C for Cat", "C for Cat", "Cat", "Cat", "k (kuh)", "C for Cat. The cat says meow meow.", "C for Cat", "🐱", "Cats can see really well in the dark!", null, emptyList(), null, true),
        LearningItem("en_d", LearningCategory.ENGLISH_ALPHABETS, "D", "d", "D for Dog", "D for Dog", "Dog", "Dog", "d (duh)", "D for Dog. The dog is our best friend.", "D for Dog", "🐶", "Dogs love to wag their tails when happy!", null, emptyList(), null, true),
        LearningItem("en_e", LearningCategory.ENGLISH_ALPHABETS, "E", "e", "E for Elephant", "E for Elephant", "Elephant", "Elephant", "e (eh)", "E for Elephant. The elephant has big ears.", "E for Elephant", "🐘", "Elephants can use their trunks like hands!", null, emptyList(), R.drawable.img_elephant_animal, true),
        LearningItem("en_f", LearningCategory.ENGLISH_ALPHABETS, "F", "f", "F for Fish", "F for Fish", "Fish", "Fish", "f (fuh)", "F for Fish. Fish swims in the river.", "F for Fish", "🐟", "Fish breathe underwater using gills!", null, emptyList(), null, true),
        LearningItem("en_g", LearningCategory.ENGLISH_ALPHABETS, "G", "g", "G for Grapes", "G for Grapes", "Grapes", "Grapes", "g (guh)", "G for Grapes. Grapes are sweet and juicy.", "G for Grapes", "🍇", "Grapes grow in big tasty bunches!"),
        LearningItem("en_h", LearningCategory.ENGLISH_ALPHABETS, "H", "h", "H for House", "H for House", "House", "House", "h (huh)", "H for House. We live in a cozy house.", "H for House", "🏠", "A house is filled with love and warmth!"),
        LearningItem("en_i", LearningCategory.ENGLISH_ALPHABETS, "I", "i", "I for Ice Cream", "I for Ice Cream", "Ice Cream", "Ice Cream", "ɪ (ih)", "I for Ice cream. Cool and yummy treat.", "I for Ice Cream", "🍦", "Ice cream comes in mango and vanilla flavors!"),
        LearningItem("en_j", LearningCategory.ENGLISH_ALPHABETS, "J", "j", "J for Jug", "J for Jug", "Jug", "Jug", "dʒ (juh)", "J for Jug. Water in the jug.", "J for Jug", "🫖", "A jug pours cool water into glasses!"),
        LearningItem("en_k", LearningCategory.ENGLISH_ALPHABETS, "K", "k", "K for Kite", "K for Kite", "Kite", "Kite", "k (kuh)", "K for Kite. Kite flies in the breezy wind.", "K for Kite", "🪁", "Kites need a gentle wind to fly high!"),
        LearningItem("en_l", LearningCategory.ENGLISH_ALPHABETS, "L", "l", "L for Lion", "L for Lion", "Lion", "Lion", "l (luh)", "L for Lion. The lion is king of the jungle.", "L for Lion", "🦁", "A lion's roar can be heard from far away!", null, emptyList(), R.drawable.img_tiger_animal, true),
        LearningItem("en_m", LearningCategory.ENGLISH_ALPHABETS, "M", "m", "M for Monkey", "M for Monkey", "Monkey", "Monkey", "m (muh)", "M for Monkey. The monkey jumps on trees.", "M for Monkey", "🐒", "Monkeys love sweet bananas!", null, emptyList(), null, true),
        LearningItem("en_n", LearningCategory.ENGLISH_ALPHABETS, "N", "n", "N for Nest", "N for Nest", "Nest", "Nest", "n (nuh)", "N for Nest. Birds make cozy nests.", "N for Nest", "🪺", "Birds build nests with small twigs and leaves!"),
        LearningItem("en_o", LearningCategory.ENGLISH_ALPHABETS, "O", "o", "O for Orange", "O for Orange", "Orange", "Orange", "ɒ (aw)", "O for Orange. Bright and citrusy fruit.", "O for Orange", "🍊", "Oranges give us lots of Vitamin C!"),
        LearningItem("en_p", LearningCategory.ENGLISH_ALPHABETS, "P", "p", "P for Parrot", "P for Parrot", "Parrot", "Parrot", "p (puh)", "P for Parrot. Green feathers and red beak.", "P for Parrot", "🦜", "Parrots can copy cute whistling sounds!", null, emptyList(), R.drawable.img_parrot_bird, true),
        LearningItem("en_q", LearningCategory.ENGLISH_ALPHABETS, "Q", "q", "Q for Queen", "Q for Queen", "Queen", "Queen", "kw (kwuh)", "Q for Queen. The queen wears a shiny crown.", "Q for Queen", "👑", "The queen is graceful and kind!"),
        LearningItem("en_r", LearningCategory.ENGLISH_ALPHABETS, "R", "r", "R for Rabbit", "R for Rabbit", "Rabbit", "Rabbit", "r (ruh)", "R for Rabbit. Hop hop goes the bunny.", "R for Rabbit", "🐰", "Rabbits have super soft fluffy fur!", null, emptyList(), R.drawable.img_rabbit_animal, true),
        LearningItem("en_s", LearningCategory.ENGLISH_ALPHABETS, "S", "s", "S for Sun", "S for Sun", "Sun", "Sun", "s (suh)", "S for Sun. The sun shines warm and bright.", "S for Sun", "☀️", "The sun gives light to all plants on Earth!"),
        LearningItem("en_t", LearningCategory.ENGLISH_ALPHABETS, "T", "t", "T for Tiger", "T for Tiger", "Tiger", "Tiger", "t (tuh)", "T for Tiger. The tiger has bold stripes.", "T for Tiger", "🐯", "Tigers are strong and love swimming!", null, emptyList(), R.drawable.img_tiger_animal, true),
        LearningItem("en_u", LearningCategory.ENGLISH_ALPHABETS, "U", "u", "U for Umbrella", "U for Umbrella", "Umbrella", "Umbrella", "ʌ (uh)", "U for Umbrella. Keeps us dry in raindrops.", "U for Umbrella", "☂️", "Open your umbrella when the rain starts!"),
        LearningItem("en_v", LearningCategory.ENGLISH_ALPHABETS, "V", "v", "V for Van", "V for Van", "Van", "Van", "v (vuh)", "V for Van. The yellow van drives along.", "V for Van", "🚐", "Vans carry children safely to school!"),
        LearningItem("en_w", LearningCategory.ENGLISH_ALPHABETS, "W", "w", "W for Watch", "W for Watch", "Watch", "Watch", "w (wuh)", "W for Watch. Watch tells the correct time.", "W for Watch", "⌚", "Tick-tock says the happy watch!"),
        LearningItem("en_x", LearningCategory.ENGLISH_ALPHABETS, "X", "x", "X for Xylophone", "X for Xylophone", "Xylophone", "Xylophone", "z (ks/z)", "X for Xylophone. Plays pretty musical notes.", "X for Xylophone", "🪵", "Each wooden bar makes a different melody!"),
        LearningItem("en_y", LearningCategory.ENGLISH_ALPHABETS, "Y", "y", "Y for Yacht", "Y for Yacht", "Yacht", "Yacht", "j (yuh)", "Y for Yacht. Sailboat on the water.", "Y for Yacht", "⛵", "Yachts glide smoothly with the sea breeze!"),
        LearningItem("en_z", LearningCategory.ENGLISH_ALPHABETS, "Z", "z", "Z for Zebra", "Z for Zebra", "Zebra", "Zebra", "z (zuh)", "Z for Zebra. Black and white stripes.", "Z for Zebra", "🦓", "Every zebra has a unique pattern of stripes!", null, emptyList(), null, true)
    )

    // 4. NUMBERS 1 TO 20 (with counting objects and Hindi names)
    val numbers1To20: List<LearningItem> = listOf(
        LearningItem("num_1", LearningCategory.NUMBERS, "1", "१", "1 - One (एक)", "1 - One", "एक", "One", "wʌn (one)", "1, One, एक। एक सूरज।", "1, One. One bright sun.", "☀️", "1 means a single special item.", 1),
        LearningItem("num_2", LearningCategory.NUMBERS, "2", "२", "2 - Two (दो)", "2 - Two", "दो", "Two", "tuː (two)", "2, Two, दो। दो आँखें।", "2, Two. Two twinkling eyes.", "👀", "We have two ears to listen with care!", 2),
        LearningItem("num_3", LearningCategory.NUMBERS, "3", "३", "3 - Three (तीन)", "3 - Three", "तीन", "Three", "θriː (three)", "3, Three, तीन। तीन तितलियाँ।", "3, Three. Three colorful butterflies.", "🦋", "A triangle has three sharp corners!", 3, emptyList(), null, true),
        LearningItem("num_4", LearningCategory.NUMBERS, "4", "४", "4 - Four (चार)", "4 - Four", "चार", "Four", "fɔːr (four)", "4, Four, चार। चार पहिये।", "4, Four. Four wheels on a car.", "🚗", "A table has four steady legs!", 4),
        LearningItem("num_5", LearningCategory.NUMBERS, "5", "५", "5 - Five (पाँच)", "5 - Five", "पाँच", "Five", "faɪv (five)", "5, Five, पाँच। पाँच उँगलियाँ।", "5, Five. Five fingers on our hand.", "🖐️", "High five! Give your friend a clap!", 5),
        LearningItem("num_6", LearningCategory.NUMBERS, "6", "६", "6 - Six (छह)", "6 - Six", "छह", "Six", "sɪks (six)", "6, Six, छह। छह चमकदार सितारे।", "6, Six. Six shining stars.", "⭐", "Insects have six tiny walking legs!", 6),
        LearningItem("num_7", LearningCategory.NUMBERS, "7", "७", "7 - Seven (सात)", "7 - Seven", "सात", "Seven", "ˈsɛv.ən (seven)", "7, Seven, सात। सात रंग का इंद्रधनुष।", "7, Seven. Seven colors in a rainbow.", "🌈", "There are 7 days in every week!", 7),
        LearningItem("num_8", LearningCategory.NUMBERS, "8", "८", "8 - Eight (आठ)", "8 - Eight", "आठ", "Eight", "eɪt (eight)", "8, Eight, आठ। आठ रसीली स्ट्रॉबेरी।", "8, Eight. Eight sweet strawberries.", "🍓", "An octopus has eight wiggly arms!", 8),
        LearningItem("num_9", LearningCategory.NUMBERS, "9", "९", "9 - Nine (नौ)", "9 - Nine", "नौ", "Nine", "naɪn (nine)", "9, Nine, नौ। नौ रंगीन गुब्बारे।", "9, Nine. Nine cheerful balloons.", "🎈", "9 is the highest single digit number!", 9),
        LearningItem("num_10", LearningCategory.NUMBERS, "10", "१०", "10 - Ten (दस)", "10 - Ten", "दस", "Ten", "tɛn (ten)", "10, Ten, दस। दस उँगलियाँ दोनों हाथों में।", "10, Ten. Ten fingers across both hands.", "👐", "You've counted all the way to 10!", 10),
        LearningItem("num_11", LearningCategory.NUMBERS, "11", "११", "11 - Eleven (ग्यारह)", "11 - Eleven", "ग्यारह", "Eleven", "ɪˈlɛv.ən", "11, Eleven, ग्यारह।", "11, Eleven.", "🍎", "10 plus 1 makes 11!", 11),
        LearningItem("num_12", LearningCategory.NUMBERS, "12", "१२", "12 - Twelve (बारह)", "12 - Twelve", "बारह", "Twelve", "twɛlv", "12, Twelve, बारह। एक दर्जन में 12 केले होते हैं।", "12, Twelve. A dozen bananas has 12.", "🍌", "There are 12 months in a whole year!", 12),
        LearningItem("num_13", LearningCategory.NUMBERS, "13", "१३", "13 - Thirteen (तेरह)", "13 - Thirteen", "तेरह", "Thirteen", "ˌθɜːrˈtiːn", "13, Thirteen, तेरह।", "13, Thirteen.", "🌸", "13 pretty flowers blooming.", 13),
        LearningItem("num_14", LearningCategory.NUMBERS, "14", "१४", "14 - Fourteen (चौदह)", "14 - Fourteen", "चौदह", "Fourteen", "ˌfɔːrˈtiːn", "14, Fourteen, चौदह।", "14, Fourteen.", "🧁", "14 sweet cupcakes ready for tea.", 14),
        LearningItem("num_15", LearningCategory.NUMBERS, "15", "१५", "15 - Fifteen (पंद्रह)", "15 - Fifteen", "पंद्रह", "Fifteen", "ˌfɪfˈtiːn", "15, Fifteen, पंद्रह।", "15, Fifteen.", "🎨", "15 colors on an artist's palette.", 15),
        LearningItem("num_16", LearningCategory.NUMBERS, "16", "१६", "16 - Sixteen (सोलह)", "16 - Sixteen", "सोलह", "Sixteen", "ˌsɪksˈtiːn", "16, Sixteen, सोलह।", "16, Sixteen.", "🚀", "16 rockets soaring into space.", 16),
        LearningItem("num_17", LearningCategory.NUMBERS, "17", "१७", "17 - Seventeen (सत्रह)", "17 - Seventeen", "सत्रह", "Seventeen", "ˌsɛv.ənˈtiːn", "17, Seventeen, सत्रह।", "17, Seventeen.", "⛵", "17 sailboats in the harbor.", 17),
        LearningItem("num_18", LearningCategory.NUMBERS, "18", "१८", "18 - Eighteen (अठारह)", "18 - Eighteen", "अठारह", "Eighteen", "ˌeɪˈtiːn", "18, Eighteen, अठारह।", "18, Eighteen.", "🍭", "18 candy lollipops in a jar.", 18),
        LearningItem("num_19", LearningCategory.NUMBERS, "19", "१९", "19 - Nineteen (उन्नीस)", "19 - Nineteen", "उन्नीस", "Nineteen", "ˌnaɪnˈtiːn", "19, Nineteen, उन्नीस।", "19, Nineteen.", "🪁", "19 kites fluttering high.", 19),
        LearningItem("num_20", LearningCategory.NUMBERS, "20", "२०", "20 - Twenty (बीस)", "20 - Twenty", "बीस", "Twenty", "ˈtwɛn.ti", "20, Twenty, बीस। बहुत खूब, बीस तक पूरी गिनती!", "20, Twenty. Fantastic counting all the way to 20!", "🏆", "20 is two whole tens! Super star!", 20)
    )

    fun getAllItems(): List<LearningItem> = hindiSwar + hindiVyanjan + englishAlphabets + numbers1To20

    fun getItemsByCategory(category: LearningCategory): List<LearningItem> = when (category) {
        LearningCategory.HINDI_SWAR -> hindiSwar
        LearningCategory.HINDI_VYANJAN -> hindiVyanjan
        LearningCategory.ENGLISH_ALPHABETS -> englishAlphabets
        LearningCategory.NUMBERS -> numbers1To20
    }

    fun getAnimalAndBirdItems(): List<LearningItem> = getAllItems().filter { it.isAnimalOrBird }

    fun getItemById(id: String): LearningItem? = getAllItems().find { it.id == id }
}

