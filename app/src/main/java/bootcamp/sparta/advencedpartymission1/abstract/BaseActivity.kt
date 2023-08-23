package bootcamp.sparta.advencedpartymission1.abstract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import bootcamp.sparta.advencedpartymission1.R
import bootcamp.sparta.advencedpartymission1.main.MainActivity

open class BaseActivity : AppCompatActivity() {

    // Toolbar 타이틀 설정
    fun setToolBarTitle(layout: ConstraintLayout, title: String, isBackBtn : Boolean) {
        val toolbar = layout.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.setDisplayHomeAsUpEnabled(isBackBtn)
        }
    }

    fun toastMsg(msg: String) = Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

    // activity간 이동
    fun <T>intentChangeActivity(activity: Class<T>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}