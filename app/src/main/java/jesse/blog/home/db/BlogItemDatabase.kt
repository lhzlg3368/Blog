package jesse.blog.home.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jesse.blog.home.model.BlogItem

@Database(
    entities = [BlogItem::class],
    version = 1,
    exportSchema = false
)
abstract class BlogItemDatabase : RoomDatabase() {

    abstract fun blogItemsDao(): BlogItemDao

    companion object {
        @Volatile
        private var INSTANCE: BlogItemDatabase? = null

        fun getInstance(context: Context): BlogItemDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BlogItemDatabase::class.java, "blogitems.db"
            ).build()
    }
}