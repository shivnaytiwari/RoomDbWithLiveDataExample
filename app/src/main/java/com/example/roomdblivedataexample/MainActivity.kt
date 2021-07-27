package com.example.roomdblivedataexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var list: List<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db =
            Room.databaseBuilder(this, AppDatabase::class.java, "app_db").allowMainThreadQueries()
                .build()
        val person = db.getPersonDao().getPerson()
        person.observe(this) {
            list = it
            txt.text =
                if (it.isNullOrEmpty()) {
                    "No data found"
                } else {
                    val sb = StringBuilder()
                    it.forEach { p ->
                        sb.append(p.name).append(",").append(p.age)
                        if (sb.isNotEmpty())
                            sb.append("\n")
                    }
                    sb.toString()
                }
        }

        val names = arrayOf("John", "Ramesh", "Suresh", "Tom", "Teddy", "Arjun")
        add.setOnClickListener {
            db.getPersonDao()
                .addPerson(Person(0, names[Random.nextInt(names.size)], Random.nextInt(30, 100)))
        }
        remove.setOnClickListener {
            if (!list.isNullOrEmpty()) {
                val item = list?.get(0) as Person
                db.getPersonDao().removePerson(item)
            }
        }
    }
}