package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Jeu
import vue.VueCrée

class ControleurCreerPartie(val vue : VueCrée,val jeu : Jeu): EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent) {
        try{
            jeu.creerPartie(vue.comboBox1.value.toInt())
        }catch(e:Exception){
            TODO("traiter")
        }
        TODO("Rediriger vers une autre page")
    }
}