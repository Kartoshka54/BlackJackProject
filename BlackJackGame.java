import GameSound.SoundPlayer;

public class BlackJackGame {
    public static void main(String[] args) {
        //Well here it is, our main class where the game plays out
        //It was a long road and as a student, I'm happy that me and my project partner
        //were able to make this game. It's not perfect, but it's ours and we're proud of it!

        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.addSound("Jet Set Radio Future - Funky Dealer.wav");
        soundPlayer.playNextSound();
        soundPlayer.setLooping(true);



        BlackJackMethods bj = new BlackJackMethods();
        bj.turnOrder("player","dealer");



    }
}



