package com.example.mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.data.Post

class HomeActivity : AppCompatActivity(){

    lateinit var listPosts : ListView
    var postsArray = ArrayList<Post>()
    lateinit  var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


      val email = intent.getStringExtra("email")

         listPosts = findViewById(R.id.listPosts)

        postsArray = arrayListOf(
           Post("Babylon","Note : 6.5", "Budget : 12.435.532 €","Date de publication: 12-09-2022","Inception est un film 1 de science-fiction d'action réalisé par Christopher Nolan en 2010. Le film suit le personnage de Dom Cobb, À Derry, dans le Maine, sept adolescents ayant du mal à s'intégrer se sont regroupés au sein ...",
               R.drawable.image1
           ),
           Post("Tempete", "Note : 7.5", "Budget : 11.435.532 €","Date de publication: 102-02-2021","Inception est un film 1 de science-fiction d'action réalisé par Christopher Nolan en 2010. Le film suit le personnage de Dom Cobb, À Derry, dans le Maine, sept adolescents ayant du mal à s'intégrer se sont regroupés au sein ...",
               R.drawable.image2
           ),
            Post("Hostalga", "Note : 5.5", "Budget : 2.435.532 €","Date de publication: 01-09-2023","Inception est un film 1 de science-fiction d'action réalisé par Christopher Nolan en 2010. Le film suit le personnage de Dom Cobb, À Derry, dans le Maine, sept adolescents ayant du mal à s'intégrer se sont regroupés au sein ...",
                R.drawable.image3
            ),
            Post("16 ans", "Note : 8.3", "Budget : 14.435.532 €","Date de publication: 23-04-2012","Inception est un film 1 de science-fiction d'action réalisé par Christopher Nolan en 2010. Le film suit le personnage de Dom Cobb, À Derry, dans le Maine, sept adolescents ayant du mal à s'intégrer se sont regroupés au sein ...",
                R.drawable.image4
            ),
            Post("Rascals", "Note : 7.2", "Budget : 23.435.532 €","Date de publication: 11-05-2019","Inception est un film 1 de science-fiction d'action réalisé par Christopher Nolan en 2010. Le film suit le personnage de Dom Cobb, À Derry, dans le Maine, sept adolescents ayant du mal à s'intégrer se sont regroupés au sein ...",
                R.drawable.image5
            ),
        )
        adapter = PostsAdapter(this,  R.layout.item_post, postsArray)
        listPosts.adapter = adapter

        listPosts.setOnItemClickListener { AdapterView, view, position, id ->
            val clickedPost = postsArray[position]
            Intent(this, PostDetailActivity::class.java).also {
                  it.putExtra("titre", clickedPost.titre)
                  it.putExtra("note", clickedPost.note)
                  it.putExtra("budget", clickedPost.budget)
                  it.putExtra("publication", clickedPost.publication)
                  it.putExtra("description", clickedPost.description)

                startActivity(it)
              }
        }
             registerForContextMenu(listPosts)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        R.id.itemAdd -> {
            val intentAddMovie = Intent (this, AddMovieActivity::class.java).also {
                startActivity(it)
            }
        }
        R.id.itemConfig -> {
            val intentProfil = Intent (this, ProfilActivity::class.java).also {
                val txtEmail = "sfef"
                val txtNom = "Barry"
                val txtPrenom = "Mamadou"
                val txtAge = "25 ans"
                val image = ""
                it.putExtra("email", txtEmail)
                it.putExtra("email", image)
                it.putExtra("nom", txtNom)
                it.putExtra("age", txtAge)
                it.putExtra("prenom", txtPrenom)
                startActivity(it)
            }

        }
        R.id.itemLogout -> {

            //modal de confirmation pour se déconnecter de l'application
            showLogoutConfirmDialog()
           // finish()
        }
        }
        return super.onOptionsItemSelected(item)
    }
 fun showLogoutConfirmDialog() {
     val builder = AlertDialog.Builder(this)
     builder.setTitle("Confirmation")
     builder.setMessage("Voulez-vous vraiment quitter l'application ?")
     builder.setPositiveButton("OUI") { dialogInterface, id ->
         val editor = this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit()
         editor.remove("is_authentificated")
        finish()
     }
     builder.setNegativeButton("NON") { dialogInterface, id ->
         dialogInterface.dismiss()
     }
     builder.setNeutralButton("Annuler"){ dialogInterface, id ->
         dialogInterface.dismiss()
     }

     val alertDialog: AlertDialog = builder.create()
     alertDialog.show()
 }
    ///override fun onCreateContextMenu(
   //     menu: ContextMenu?,
    //    v: View?,
      //  menuInfo: ContextMenu.ContextMenuInfo?
   // ) {

    //    menuInflater.inflate(R.menu.list_context_menu, menu)
      //  super.onCreateContextMenu(menu, v, menuInfo)

   // }

  //  override fun onContextItemSelected(item: MenuItem): Boolean {
  //      val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
    //    val position: Int = info.position
      //  when(item.itemId){
        //    R.id.itemShow -> {
          //    Intent(this, PostDetailActivity::class.java).also {
           //       it.putExtra("titre", postsArray[position].titre)
            //      startActivity(it)
            //  }
           // }
            //    R.id.itemDelete -> {
              //      postsArray.removeAt(position)
                //    adapter.notifyDataSetChanged()
               // }
       // }
       // return super.onContextItemSelected(item)
   // }



}