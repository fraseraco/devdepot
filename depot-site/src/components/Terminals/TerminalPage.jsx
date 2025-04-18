import React, { useEffect, useRef } from 'react';
import { Terminal } from 'xterm';
import 'xterm/css/xterm.css'; // Import xterm styles
import './TerminalPage.css'; // Add custom styles for the terminal page

const TerminalPage = () => {
    const terminalRef = useRef(null);

    useEffect(() => {
        const terminal = new Terminal({
            rows: 24, // Number of rows in the terminal
            cols: 80, // Number of columns in the terminal
            theme: {
                background: '#1e1e1e', // Terminal background color
                foreground: '#ffffff', // Terminal text color
            },
        });

        // Attach the terminal to the DOM
        terminal.open(terminalRef.current);

        // Write some initial text to the terminal
        terminal.writeln('Welcome to the Full-Screen Terminal Emulator!');
        terminal.writeln('Type "help" to see available commands.');

        // Handle user input
        terminal.onData((data) => {
            terminal.write(`\r\nYou typed: ${data}`);
        });

        return () => {
            terminal.dispose(); // Clean up the terminal instance on unmount
        };
    }, []);

    return <div ref={terminalRef} className="terminal-container" />;
};

export default TerminalPage;