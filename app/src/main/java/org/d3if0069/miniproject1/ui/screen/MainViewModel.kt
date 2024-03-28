package org.d3if0069.miniproject1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0069.miniproject1.model.Catatan

class MainViewModel: ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<Catatan> {
        val data = mutableListOf<Catatan>()
            data.add(
                Catatan(
                    "1",
                    "Paket Bukber hemat",
                    "Buat kalian yang pengen bukber dengan harga ramah kantong harus cobain sih!! Harga oke rasa mantap",
                    "37.500 aja!"
                )
            )
        data.add(
            Catatan(
                "2",
                "Paket Bukber Santai",
                "Buat kalian yang pengen bukber dengan harga ramah kantong harus cobain sih!! Harga oke rasa mantap",
                "48.000 aja!"
            )
        )
        data.add(
            Catatan(
                "3",
                "Paket Bukber Asik",
                "Buat kalian yang pengen bukber dengan harga ramah kantong harus cobain sih!! Harga oke rasa mantap",
                "65.000 aja!"
            )
        )
        data.add(
            Catatan(
                "4",
                "Paket Bukber mewah",
                "Buat kalian yang pengen bukber dengan harga ramah kantong harus cobain sih!! Harga oke rasa mantap",
                "89.500 aja!"
            )
        )
        data.add(
            Catatan(
                "5",
                "Paket Bukber dating",
                "Buat kalian yang pengen bukber bareng pacar kita menyediakan loh!! Khusus ber 2",
                "45.500 aja!"
            )
        )
        data.add(
            Catatan(
                "6",
                "Paket Bukber dating",
                "Buat kalian yang pengen bukber dengan harga ramah kantong harus cobain sih!! Harga oke rasa mantap",
                "45.500 aja!"
            )
        )

        return data
    }
}