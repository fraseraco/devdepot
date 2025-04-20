import React, { useEffect, useRef } from 'react';
import { Terminal } from 'xterm';
import 'xterm/css/xterm.css'; // Import xterm styles
import './TerminalPage.css'; // Add custom styles for the terminal page

const TerminalPage = () => {
    const terminalRef = useRef(null);

    useEffect(() => {
        const terminal = new Terminal({
            rows: 40, // Number of rows in the terminal
            cols: 160, // Number of columns in the terminal
            theme: {
                background: '#1e1e1e', // Terminal background color
                foreground: '#ffffff', // Terminal text color
                scrollback: 0, // Disable scrollback to prevent scrollbar
                overflow: 'hidden !important'
            },
        });

        // Attach the terminal to the DOM
        terminal.open(terminalRef.current);

        // Write some initial text to the terminal
        terminal.writeln('Welcome to /dev/depot!');
        terminal.writeln('Type "help" to see available commands.');

        let inputBuffer = ''; // Buffer to store user input

        // Handle user input
        terminal.onData((data) => {
            if (data === '\r') { // Enter key pressed
                terminal.write('\r\n'); // Move to the next line

                if (inputBuffer.trim() === 'help') {
                    // Display help menu
                    terminal.writeln('Available Commands:');
                    terminal.writeln('help - Show this help menu');
                    terminal.writeln('clear - Clear the terminal');
                    terminal.writeln('exit - Exit the terminal');
                    terminal.writeln('search <term> - Search for an item in the shop');
                    terminal.writeln('add <item-sku> - Add an item to the cart');
                    terminal.writeln('cart <command> - Manage the shopping cart');
                    terminal.writeln('checkout - Proceed to checkout');
                    terminal.writeln('signin - Sign in to your account');

                } else if (inputBuffer.trim() === 'clear') {
                    terminal.clear(); // Clear the terminal
                } else if (inputBuffer.trim() === 'exit') {
                    terminal.writeln('Exiting terminal...');
                } 
                else if(inputBuffer.trim().startsWith('search')) {
                    const searchTerm = inputBuffer.trim().substring(7);
                    terminal.writeln(`Searching for "${searchTerm}"...`);
                    fetch('/products/all')
                        .then((response) => {
                            if (!response.ok) {
                                throw new Error('Failed to fetch products');
                            }
                            return response.json();
                        })
                        .then((products) => {
                            const results = products.filter((product) =>
                                product.name.toLowerCase().includes(searchTerm.toLowerCase())
                            );

                            if (results.length > 0) {
                                terminal.writeln('Search Results:');
                                results.forEach((product) => {
                                    terminal.writeln(`- ${product.name} (SKU: ${product.sku})`);
                                });
                            } else {
                                terminal.writeln('No products found.');
                            }
                        })
                        .catch((error) => {
                            terminal.writeln(`Error: ${error.message}`);
                        });
                }
                else {
                    terminal.writeln(`Unknown command: ${inputBuffer.trim()}`);
                }

                inputBuffer = ''; // Clear the input buffer
            } else if (data === '\u007F') { // Handle backspace
                if (inputBuffer.length > 0) {
                    inputBuffer = inputBuffer.slice(0, -1);
                    terminal.write('\b \b'); // Erase the last character
                }
            } else {
                inputBuffer += data; // Append character to buffer
                terminal.write(data); // Echo the character
            }
        });

        return () => {
            terminal.dispose(); // Clean up the terminal instance on unmount
        };
    }, []);

    return <div ref={terminalRef} className="terminal-container" />;
};

export default TerminalPage;