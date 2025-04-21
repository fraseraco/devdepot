import React, { useEffect, useRef } from 'react';
import { Terminal } from 'xterm';
import 'xterm/css/xterm.css';
import './TerminalPage.css';

const TerminalPage = () => {
    const terminalRef = useRef(null);

    useEffect(() => {
        const terminal = new Terminal({
            rows: 40,
            cols: 160,
            theme: {
                background: '#1e1e1e',
                foreground: '#ffffff',
                scrollback: 0,
                overflow: 'hidden !important'
            },
        });

        terminal.open(terminalRef.current);

        terminal.writeln('Welcome to /dev/depot!');
        terminal.writeln('Type "help" to see available commands.');

        let inputBuffer = '';

        terminal.onData((data) => {
            if (data === '\r') {
                terminal.write('\r\n');

                if (inputBuffer.trim() === 'help') {
                    terminal.writeln('Available Commands:');
                    terminal.writeln('help - Show this help menu');
                    terminal.writeln('clear - Clear the terminal');
                    terminal.writeln('exit - Exit the terminal');
                    terminal.writeln('search <term> - Search for an item in the shop');
                    terminal.writeln('cart add <product-id> - Add to the shopping cart');
                    terminal.writeln('cart display - Display the shopping cart');
                    terminal.writeln('checkout - Proceed to checkout');
                    terminal.writeln('signin - Sign in to your account');
                } else if (inputBuffer.trim() === 'clear') {
                    terminal.clear();
                } else if (inputBuffer.trim() === 'exit') {
                    terminal.writeln('Exiting terminal...');
                } else if (inputBuffer.trim().startsWith('search')) {
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
                                    terminal.writeln(`- ${product.name} (Product ID: ${product.id})`);
                                });
                            } else {
                                terminal.writeln('No products found.');
                            }
                        })
                        .catch((error) => {
                            terminal.writeln(`Error: ${error.message}`);
                        });
                } else if (inputBuffer.trim().startsWith('cart add')) {
                    const productId = inputBuffer.trim().substring(9);
                    terminal.writeln(`Adding product ID ${productId} to the cart...`);
                    fetch('/carts/items', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem('authToken')}`,
                        },
                        body: JSON.stringify({
                            productId: parseInt(productId, 10),
                            quantity: 1,
                        }),
                    })
                        .then((response) => {
                            if (response.ok) {
                                terminal.writeln('Product added to the cart successfully.');
                            } else {
                                throw new Error('Failed to add product to the cart');
                            }
                        })
                        .catch((error) => {
                            terminal.writeln(`Error: ${error.message}`);
                        });
                } else if (inputBuffer.trim() === 'cart display') {
                    terminal.writeln('Fetching cart contents...');
                    fetch('/carts/me', {
                        method: 'GET',
                        headers: {
                            'Authorization': `Bearer ${localStorage.getItem('authToken')}`,
                        },
                    })
                        .then((response) => {
                            if (!response.ok) {
                                throw new Error('Failed to fetch cart contents');
                            }
                            return response.json();
                        })
                        .then((cart) => {
                            console.log(cart);
                            const { cartItems } = cart;
                            if (cartItems && cartItems.length > 0) {
                                terminal.writeln('Cart Contents:');
                                cartItems.forEach((item) => {
                                    terminal.writeln(
                                        `- ${item.product.name} (Product ID: ${item.product.id}, Price: $${item.product.price.toFixed(2)}, Quantity: ${item.quantity})`
                                    );
                                });
                            } else {
                                terminal.writeln('Your cart is empty.');
                            }
                        })
                        .catch((error) => {
                            terminal.writeln(`Error: ${error.message}`);
                        });
                } else if (inputBuffer.trim().startsWith('signin')) {
                    const [username, password] = inputBuffer.trim().substring(7).split(' ');
                    terminal.writeln(`Signing in as ${username}...`);
                    fetch('/auth/login', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Accept': 'application/json',
                        },
                        body: JSON.stringify({
                            username: username,
                            password: password,
                        }),
                    })
                        .then((response) => {
                            if (response.ok) {
                                return response.json().then((data) => {
                                    localStorage.setItem('authToken', data.token);
                                });
                            } else {
                                throw new Error('Failed to sign in');
                            }
                        })
                        .then((data) => {
                            terminal.writeln(`Welcome back, ${username}!`);
                        })
                        .catch((error) => {
                            terminal.writeln(`Error: ${error.message}`);
                        });
                } else {
                    terminal.writeln(`Unknown command: ${inputBuffer.trim()}`);
                }

                inputBuffer = '';
            } else if (data === '\u007F') {
                if (inputBuffer.length > 0) {
                    inputBuffer = inputBuffer.slice(0, -1);
                    terminal.write('\b \b');
                }
            } else {
                inputBuffer += data;
                terminal.write(data);
            }
        });

        return () => {
            terminal.dispose();
        };
    }, []);

    return <div ref={terminalRef} className="terminal-container" />;
};

export default TerminalPage;