import React, { useState } from 'react';
import './SignIn.css';

const SignIn = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState(''); // Add email state
    const [role, setRole] = useState(''); // Add role state


    
    const handleSignIn = async () => {
        try {
            const response = await fetch('/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                }),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Login successful:', data);

                localStorage.setItem('authToken', data.token);
                window.location.href = '/';
            } else {
                console.error('Login failed:', response.status);
                alert('Invalid username or password. Please try again.');
            }
        } catch (error) {
            console.error('Error during login:', error);
        }
    };

    const handleSignUp = async () => {
        try {
            const response = await fetch('/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                    email: `${username}@gmail.com`,
                    role: 'ADMIN',
                }),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Registration successful:', data);

                alert('Registration successful! You can now log in.');
            } else {
                console.error('Registration failed:', response.status);
                alert('Registration failed. Please try again.');
            }
        } catch (error) {
            console.error('Error during registration:', error);
        }
    };

    return (
        <div className='sign-in-container'>
            <h1>Welcome to Dev Depot</h1>
            <div className='form-group'>
                <input
                    type='text'
                    placeholder='Username'
                    className='input-field'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type='password'
                    placeholder='Password'
                    className='input-field'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                
            </div>
            <div className='button-group'>
                <button className='btn' onClick={handleSignIn}>
                    Sign In
                </button>
                <button className='btn' onClick={handleSignUp}>
                    Sign Up
                </button>
            </div>
        </div>
    );
};

export default SignIn;