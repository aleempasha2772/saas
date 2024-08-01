import React from 'react';
import '../CSS/Header.css';
import { MDBInputGroup, MDBInput, MDBIcon, MDBBtn } from 'mdb-react-ui-kit';
const Header: React.FC = () => {
  return (


    <header className="header">
      <div>
        <h1>
          Overwiew
        </h1>
      </div>
    <div>
      <MDBInputGroup>
        <MDBInput label='Search' />
        <MDBBtn rippleColor='dark'>
          <MDBIcon icon='search' />
        </MDBBtn>
      </MDBInputGroup>  
    </div> 
    
      <nav className="header__nav">
        <a href="#home" className="header__link">Profile</a>
      </nav>
    </header>
  );
}

export default Header;
