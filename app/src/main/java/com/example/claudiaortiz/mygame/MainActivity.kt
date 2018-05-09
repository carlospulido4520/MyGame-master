package com.example.claudiaortiz.mygame

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity() : AppCompatActivity(),View.OnClickListener, Parcelable {


    override fun onClick(v: View?) {
        btnSelected(v as Button)

    }

    private var cells= mutableMapOf<Int, String>()
    private var isX=true
    private var winner:String=""
    private val totalCell=9
    private lateinit var txtResult:TextView
    private val x= "X"
    private val o= "O"
    private var btns= arrayOfNulls<Button>(totalCell)
    private val combinaciones:Array<IntArray> = arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(3,4,5),
            intArrayOf(6,7,8),
            intArrayOf(0,3,6),
            intArrayOf(1,4,7),
            intArrayOf(2,5,8),
            intArrayOf(0,4,8),
            intArrayOf(2,4,6)
    )

    constructor(parcel: Parcel) : this() {
        isX = parcel.readByte() != 0.toByte()
        winner = parcel.readString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult=findViewById(R.id.txtResult)

        for(i in 1..totalCell){
            var button= findViewById<Button>(resources.getIdentifier("btn$i","id",packageName))
            button.setOnClickListener(this)
            button?.setBackgroundColor(Color.TRANSPARENT)
            btns [i-1]=button

        }
    }
    private fun btnSelected(button: Button){
        var index=0
        when(button.id){
            R.id.btn1-> index=0
            R.id.btn2-> index=1
            R.id.btn3-> index=2
            R.id.btn4-> index=3
            R.id.btn5-> index=4
            R.id.btn6-> index=5
            R.id.btn7-> index=6
            R.id.btn8-> index=7
            R.id.btn9-> index=8
        }
        playGame(index,button)
        chechWinner()
        update()
    }
    private fun chechWinner(){
        if(cells.isNotEmpty()){
            for (combinacion in combinaciones){
                var(a,b,c)=combinacion

                if (cells[a] !=null && cells[a]==cells[b] && cells[a]== cells[c]){
                    this.winner=cells[a].toString()
                }
            }
        }
    }

    private fun update(){
        when{
            winner.isNotEmpty()->{
                txtResult.text= resources.getString(R.string.Winner,winner)
                txtResult.setTextColor(Color.GREEN)
            }
            cells.size==totalCell->{
                txtResult.text="Empate"
            }
            else->
                txtResult.text=resources.getString(R.string.next_player,if(isX)x else o)
        }
    }
    private fun playGame(index:Int,button: Button){
        if(!winner.isNullOrEmpty()){
            Toast.makeText(this,"Juego Finalizado",Toast.LENGTH_LONG).show()
            return
        }

        when{
            isX->cells[index]=x
            else->cells[index]=o
        }
        button.text=cells[index]
        if(cells[index] == o){
            button.setTextColor(Color.WHITE)
        }else{
            button.setTextColor(Color.YELLOW)
        }
        button.isEnabled=false
        isX=!isX
    }
    fun resetButton(){
        for(i in 1..totalCell){
            var button=btns[i-1]
            button?.text=""
            button?.setBackgroundColor(Color.TRANSPARENT)
            button?.isEnabled=true
        }
    }
    fun newGame(){
        cells= mutableMapOf()
        isX=true
        winner=""
        txtResult.text=resources.getString(R.string.next_player,x)
        txtResult.setTextColor(Color.WHITE)
        resetButton()
    }

    fun reset(view: View){
        newGame()
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isX) 1 else 0)
        parcel.writeString(winner)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
