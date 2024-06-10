package controleur.game

import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Task
import javafx.scene.image.Image
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.serverData.Plateau
import vue.Game
import java.io.FileInputStream

class GameBackgound(val jeu : Jeu,val game : Game) {
    fun startWaiting(){
        val task = object : Task<Unit>() {
            override fun call() {
                // TEST : Modif carte
                game.defausse[0].value = Image(FileInputStream("images/cartes/carte0.png"))
                // ---
                do{
                    var data : Plateau?
                    runBlocking {
                        data = jeu.getPartieState()
                        if(data!!.plateaux[data!!.indexJoueurCourant].idJoueur == jeu.myPlayer!!.id){
                            UpdatePlaying(game,true)
                            jeu.myturnToPlay = true
                        }else{
                            UpdatePlaying(game,false)
                            jeu.myturnToPlay = false
                        }
                    }
                    Thread.sleep(3000)
                } while (data!!.etape != "PARTIE_TERMINEE")
            }
        }
        val thread = Thread(task)
        thread.start()
    }
}