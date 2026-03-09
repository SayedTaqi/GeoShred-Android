package com.example.geoshred;

import android.media.midi.MidiManager;
import android.media.midi.MidiDevice;
import android.media.midi.MidiInputPort;

public class MidiController {

    private MidiManager midiManager;
    private MidiDevice midiDevice;
    private MidiInputPort midiInputPort;

    public MidiController(MidiManager manager) {
        this.midiManager = manager;
    }

    public void connect(MidiDevice device) {
        this.midiDevice = device;
        // Handle MIDI device connection and port setup
    }

    public void sendNoteOn(int channel, int note, int velocity) {
        byte[] message = new byte[3];
        message[0] = (byte)(0x90 | channel); // Note On Command
        message[1] = (byte)note; // Note Number
        message[2] = (byte)velocity; // Velocity
        sendMidiMessage(message);
    }

    public void sendNoteOff(int channel, int note, int velocity) {
        byte[] message = new byte[3];
        message[0] = (byte)(0x80 | channel); // Note Off Command
        message[1] = (byte)note; // Note Number
        message[2] = (byte)velocity; // Velocity
        sendMidiMessage(message);
    }

    public void sendPitchBend(int channel, int bend) {
        byte[] message = new byte[3];
        message[0] = (byte)(0xE0 | channel); // Pitch Bend Command
        message[1] = (byte)(bend & 0x7F); // LSB
        message[2] = (byte)((bend >> 7) & 0x7F); // MSB
        sendMidiMessage(message);
    }

    public void sendModulation(int channel, int value) {
        byte[] message = new byte[3];
        message[0] = (byte)(0xB0 | channel); // Control Change Command
        message[1] = (byte)1; // Modulation Control
        message[2] = (byte)value; // Modulation Value
        sendMidiMessage(message);
    }

    private void sendMidiMessage(byte[] message) {
        if (midiInputPort != null) {
            try {
                midiInputPort.send(message, 0, message.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}