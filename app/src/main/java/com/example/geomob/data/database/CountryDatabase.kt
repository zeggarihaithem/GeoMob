package com.example.geomob.data.database

import android.content.Context
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.geomob.R
import com.example.geomob.data.dao.CountryDao
import com.example.geomob.data.entity.*

@Database(
    entities = [Country::class, History::class, Video::class, SlideShow::class, Twitter::class],
    version = 1,
    exportSchema = false
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao?

    companion object {
        private var instance: CountryDatabase? = null
        @Synchronized
        fun getInstance(context: Context): CountryDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java, "countries_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance
        }

        private val roomCallback: Callback = object : Callback() {
              override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                  super.onCreate(db)
                  instance?.let { PopulateDbAsyncTask(it).execute() }
              }
          }

          private open class PopulateDbAsyncTask(db: CountryDatabase) :
              AsyncTask<Void?, Void?, Void?>() {
              private val countryDao : CountryDao = db.countryDao()!!
              override fun doInBackground(vararg params: Void?): Void? {
                  countryDao.addCountries(
                      arrayListOf(
                          Country(
                              1,
                              "Algeria",
                              "algiers",
                              "North Africa Country",
                              "2m",
                              "40m",
                              R.drawable.ic_algeria,
                              R.raw.algeria,
                              "",
                              ""
                          ),
                          Country(
                              2,
                              "Tunisia",
                              "Tunis",
                              "North Africa Country ",
                              "163k",
                              "11m",
                              R.drawable.ic_tunisia,
                              R.raw.tunisia,
                              "",
                              ""
                          ),
                          Country(
                              3,
                              "Morocco",
                              "rabat",
                              "North Africa Country",
                              "446k",
                              "35m",
                              R.drawable.ic_morocco,
                              R.raw.morocco,
                              "",
                              ""
                          ),
                          Country(
                              4,
                              "Egypt",
                              "cairo",
                              "North Africa Country",
                              "1m",
                              "98m",
                              R.drawable.ic_egypt,
                              R.raw.egypt,
                              "",
                              ""
                          ),
                          Country(
                              5,
                              "Palestine",
                              "kodos",
                              "asia",
                              "446k",
                              "35m",
                              R.drawable.ic_palestine,
                              R.raw.palestine,
                              "",
                              ""
                          )
                      )
                  )
                  countryDao.addSlideshow(
                      arrayListOf(
                          SlideShow(
                              1,
                              1,
                              "https://images.unsplash.com/photo-1593018962290-b86d9317cc02?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80"
                          ),
                          SlideShow(
                              2,
                              1,
                              "https://images.unsplash.com/photo-1588676449872-3eaa8b242e69?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                          ),
                          SlideShow(
                              3,
                              1,
                              "https://images.unsplash.com/photo-1566733239903-751f815b0acd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                          )
                      )
                  )
                  countryDao.addVideos(
                      arrayListOf(
                          Video(
                              1,
                              1,
                              "Silent desert",
                              "https://www.videvo.net/videvo_files/converted/2015_09/preview/Sahara.mp496407.webm?v=1651769"

                          ),
                          Video(
                              2,
                              1,
                              "Panning on a Vintage Map Across to Africa",
                              "https://www.videvo.net/videvo_files/converted/2016_08/preview/160712_087_Maps_Africa_1080p.mp425941.webm"
                          ),
                          Video(
                              3,
                              1,
                              "Algerian flag",
                              "https://media.istockphoto.com/videos/algerian-flag-video-id881016288"

                          )
                      )
                  )

                  countryDao.addTwitter(
                      arrayListOf(
                          Twitter(
                              1,
                              1,
                              "https://twitter.com/Algeria_Tweet/status/1269659428610994176"
                          ),
                          Twitter(
                              2,
                              1,
                              "https://twitter.com/Algeria_Tweet/status/1261124077625856000"
                          )
                      )
                  )

                  countryDao.addHistories(
                      arrayListOf(
                          History(
                              1,
                              1,
                              "01-11-1954",
                              "The Algerian liberation revolution or the Algerian war, is a war that broke out in Fatih November 1954 with the participation of about 1,200 Mujahideen",
                              "https://www.aljazeera.net/wp-content/uploads/2020/03/d6aec8b8-59ac-4dd7-8b46-f51d8651a577.jpeg"
                          ),
                          History(
                              2,
                              1,
                              "05-07-1962",
                              "Algeria is liberated from French colonialism that lasted more than thirteen decades (132 years), thanks to the bombing of the great editorial revolution, which is the largest revolution in the twentieth century.",
                              "https://image.shutterstock.com/image-vector/arabic-calligraphy-design-istiqlal-day-260nw-1096595426.jpg"
                          )
                      )
                  )

                  return null
              }
          }
    }

}