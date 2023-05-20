import GameSound.SoundPlayer;

public class BlackJackGame {
    public static void main(String[] args) {

        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.addSound("Jet Set Radio Future - Funky Dealer.wav");
        soundPlayer.playNextSound();
        soundPlayer.setLooping(true);



        BlackJackMethods bj = new BlackJackMethods();
        bj.turnOrder("player","dealer");



    }
}



