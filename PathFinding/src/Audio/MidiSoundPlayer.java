package Audio;


import java.util.ArrayList;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
//import javax.sound.midi.Soundbank;
//import javax.sound.midi.*;


public class MidiSoundPlayer {
    private final ArrayList<Integer> keys;
    private Synthesizer synth;
    private final MidiChannel channel;
    
    private final int inputValueMaximum;
    private static int CACHED_INDEX = -1;

    public MidiSoundPlayer(int maxValue) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        inputValueMaximum = maxValue;
        
        
        //Set up midi channel
        channel = synth.getChannels()[0];

        //Electric grand piano sounds the best, so it tries to find that.
        //Sometimes it is not supported, so it defaults to 143
        System.out.println(synth.getDefaultSoundbank().getName());
        Instrument[] instruments = synth.getDefaultSoundbank().getInstruments();
        //instruments = Soundbank.getInstruments();
        if (CACHED_INDEX == - 1) {
            boolean found = false;
            int index;
            for(Instrument i: instruments) {
            	System.out.println(i.getName());
            }
            for(index = 0; index < instruments.length; index++) {
            	Instrument i = instruments[index];
            	//Steel Drums, Glockenspiel, , Marimba
                if (i.getName().contains("Woodblock")) {
                    found = true;
                    CACHED_INDEX = index;
                    System.out.println("\n"+i.getName());
                    break;
                }
            }
            if (!found) {
                index = 50;
                System.out.println("could not find");
            }
            CACHED_INDEX = index;
        }
        
        System.out.println("\n"+instruments[CACHED_INDEX].getName());

        channel.programChange(instruments[CACHED_INDEX].getPatch().getProgram());
        
        //Set up keys
        keys = new ArrayList<>();
        //24 is the start of the somewhat audible major keys
        //108 is the last decent
        for (int i = 24; i < 108; i += 12) {
            keys.add(i);
            keys.add(i + 2);
            keys.add(i + 4);
            keys.add(i + 5);
            keys.add(i + 7);
            keys.add(i + 9);
            keys.add(i + 11);
        }
    }
    
    private int convertToMajor(int v) {
        float n = ((float)v / (float)inputValueMaximum);
        int index = (int)(n * (float)keys.size());
        index = Math.max(1, Math.min(107, index));
        return keys.get(index);
    }
    
    public void makeSound(int value) {
        int note = convertToMajor(value);
        channel.noteOn(note, 25);
    }
}
