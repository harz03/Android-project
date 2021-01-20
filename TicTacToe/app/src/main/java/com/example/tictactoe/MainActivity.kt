package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener{
    var Player = true
    var Turn_count = 0
    var board_status = Array(3){IntArray(3)}

    lateinit var  board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
                arrayOf(btn,btn2,btn3),
                arrayOf(btn4,btn5,btn6),
                arrayOf(btn7,btn8,btn9),
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener {
            Player = true
            Turn_count = 0
            initializeBoardStatus()
            displayTv.text = "Player X Turn"
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                board_status[i][j] = -1

            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn->{
                updateValue(row = 0,col = 0, player=Player)
            }
            R.id.btn2->{
                updateValue(row = 0,col = 1, player=Player)
            }
            R.id.btn3->{
                updateValue(row = 0,col = 2, player=Player)
            }
            R.id.btn4->{
                updateValue(row = 1,col = 0, player=Player)
            }
            R.id.btn5->{
                updateValue(row = 1,col = 1, player=Player)

            }
            R.id.btn6->{
                updateValue(row = 1,col = 2, player=Player)

            }
            R.id.btn7->{
                updateValue(row = 2,col = 0, player=Player)

            }
            R.id.btn8->{
                updateValue(row = 2,col = 1, player=Player)

            }
            R.id.btn9->{
                updateValue(row = 2,col = 2, player=Player)
            }

        }
        Turn_count++
        Player = !Player
        if(Player && Turn_count <9){
           displayTv.text = "Player X Turn"
        }else if(!Player && Turn_count <9){
            displayTv.text = "Player O Turn"
        }
        else{
            displayTv.text="DRAW"
        }
        checkWinner()
    }

    private fun checkWinner() {
        var bl = true
        for(i in 0..2){
            if(displayTv.text.contains("Wins")){
                disableButton()
            }
            if(board_status[i][0]== board_status[i][1] && board_status[i][0]== board_status[i][2] && bl){
                if(board_status[i][0]==1) {
                    displayTv.text = "Player X Wins"
                    bl=false
                }else if(board_status[i][0] == 0){
                    displayTv.text = "Player O Wins"
                    bl = false
                }
            }
        }
        for(i in 0..2){
            
            if(board_status[0][i]== board_status[1][i] && board_status[0][i]== board_status[2][i]){

                if(board_status[0][i]==1) {
                    displayTv.text = "Player X Wins"
                    disableButton()
                    break;
                }else if(board_status[0][i]== 0){
                    displayTv.text = "Player O Wins"
                    disableButton()
                    break;
                }
            }
        }
        if(board_status[0][0] == board_status[1][1]  && board_status[0][0] == board_status[2][2] ){

            if(board_status[0][0]==1) {
                displayTv.text = "Player X Wins"
                disableButton()
            }else if(board_status[0][0]== 0){
                displayTv.text = "Player O Wins"
                disableButton()
            }
        }
        if(board_status[0][2] == board_status[1][1]  && board_status[2][0] == board_status[1][1] ){
//            if(displayTv.text.contains("Wins")){
//                disableButton()
//            }
            if(board_status[2][0]==1) {
                displayTv.text = "Player X Wins"
                disableButton()
            }else if(board_status[2][0]== 0){
                displayTv.text = "Player O Wins"
                disableButton()
            }
        }
    }

    private fun disableButton() {
        for(i in board){
            for(j in i){
                j.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        var text = if(player) "X" ;else "O";
        var value:Int = if(player) 1;else 0;
        board[row][col].apply {
            isEnabled = false
            setText(text)
            board_status[row][col] = value
        }
    }
}