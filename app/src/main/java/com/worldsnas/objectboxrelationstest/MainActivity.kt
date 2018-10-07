package com.worldsnas.objectboxrelationstest

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.system.Os.link
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.worldsnas.objectboxrelationstest.entity.StudentEntity
import com.worldsnas.objectboxrelationstest.entity.StudentEntity_
import com.worldsnas.objectboxrelationstest.entity.TeacherEntity
import com.worldsnas.objectboxrelationstest.entity.TeacherEntity_
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        val teacherBox = box.boxFor<TeacherEntity>()

        val teacher = createFakeTeacher()

        teacherBox.put(teacher)

        teacher.students.addAll(createFakeStudents())
        teacher.students.applyChangesToDb()

        teacherBox.put(teacher)

        teacherBox.closeThreadResources()

        retrieveStudents()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createFakeStudents() : List<StudentEntity>{
        val students = LinkedList<StudentEntity>()

        for (i in 0 until 5){
            val s = StudentEntity()
            val id =i*100L
            s.id = id
            s.name = "name$id"
            s.address = "ad$id"
            s.phone = 10 + i
            students.add(s)
        }
        return students
    }

    private fun createFakeTeacher() = TeacherEntity().also {
        it.id = 100
        it.cache = System.currentTimeMillis()
    }


    private fun retrieveStudents(){
        val studentBox = box.boxFor<StudentEntity>()

        val query = studentBox.query {
            link(StudentEntity_.teacher).equal(TeacherEntity_.id, 100)
        }

        val students = query.find()

        for (student in students) {
            Log.d("MainActivity", student.toString())
        }
    }
}
