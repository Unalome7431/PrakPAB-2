package com.example.lokacaraaap

data class EventData(
    val title: String,
    val date: String,
    val location: String,
    val price: String,
    val imageRes: Int,
    val description: String = "Deskripsi belum tersedia untuk event ini."
)

fun getDummyEvents(): List<EventData> {
    return listOf(
        EventData(
            "Seminar Nasional: Masa Depan AI di Industri Kreatif",
            "24 Okt 2023 • 09:00",
            "Auditorium UNS, Surakarta",
            "Gratis",
            R.drawable.event,
            "Seminar ini akan membahas dampak AI pada industri kreatif di Indonesia, khususnya bagi mahasiswa UNS."
        ),
        EventData(
            "Festival Budaya: Harmoni Nusantara",
            "28 Okt 2023 • 15:00",
            "Pamedan Mangkunegaran",
            "Rp 25.000",
            R.drawable.band,
            "Pertunjukan seni dan budaya nusantara yang dimeriahkan oleh seniman lokal Surakarta."
        ),
        EventData(
            "Workshop UI/UX: Crafting High-End Portfolios",
            "30 Okt 2023 • 10:00",
            "Creative Space Solo",
            "Gratis",
            R.drawable.event,
            "Pelatihan intensif membuat portofolio desain yang profesional untuk pemula."
        )
    )
}