package controleur.game

import javafx.event.EventHandler
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import kotlinx.coroutines.runBlocking
import modele.Jeu
import org.controlsfx.control.spreadsheet.Grid
import vue.Game

class ClickCard(val pane : GridPane,val jeu : Jeu,val game : Game) {
    init {
        for (i in 0..pane.children.size-1){
            (pane.children[i] as ImageView).onMouseClicked = ControleurClickCard((pane.children[i] as ImageView),jeu,game)
        }
    }
}

class ControleurClickCard(val img : ImageView,val jeu : Jeu,val game : Game):EventHandler<MouseEvent>{
    override fun handle(e: MouseEvent) {
        if(jeu.playingChoice == null){
            return
        }
        var row = GridPane.getRowIndex(img) + 1
        var column = GridPane.getColumnIndex(img) + 1
        if(jeu.playingChoice == "Defausse"){
            runBlocking { jeu.echangedefausse(column,row) }
            val gameState = UpdateGameState(game, jeu)
            runBlocking { jeu.getPartieState() }
            gameState.update()
        }
    }
}