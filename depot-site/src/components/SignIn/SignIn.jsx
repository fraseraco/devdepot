import React from 'react';
import './SignIn.css';

const SignIn = () => {
    return (
        <div className='sign-in-container'>
            <h1>Welcome to Dev Depot</h1>
            <div className='form-group'>
                <input type='text' placeholder='Username' className='input-field' />
                <input type='password' placeholder='Password' className='input-field' />
            </div>
            <div className='button-group'>
                <button className='btn'>Sign In</button>
                <button className='btn'>Sign Up</button>
            </div>
        </div>
    );
};

export default SignIn;