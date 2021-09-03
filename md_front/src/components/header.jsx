import React from 'react';
import styled from 'styled-components';

function Header() {
    return (
        <div>  
            <Head>MMS Proect</Head>
        </div>
    );
}

const Head = styled.h1`
justify-content: center;
align-items: center;
width: 50vw;
flex-direction: column;
margin: 0 auto;
`;

export default Header;