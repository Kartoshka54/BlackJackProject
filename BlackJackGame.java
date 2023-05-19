import GameSound.sound;

public class BlackJackGame {
    public static void main(String[] args) {

        String audioFilePath = "C://Users//EVEREST//Desktop//Uni Year 2 (Second Semester)//Data Structures//BlackJack Project//BlackJack//src//GameSound//Jet Set Radio Future - Funky Dealer.wav";
        sound.SoundPlayer.playSound(audioFilePath);

        BlackJackMethods bj = new BlackJackMethods();
        bj.turnOrder("player","dealer");



    }
}



