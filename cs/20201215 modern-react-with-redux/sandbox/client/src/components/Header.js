import React from 'react';
import { Link } from 'react-router-dom';
import GoogleAuth from './GoogleAuth';

const Header = () => {
  return (
    <div className="ui secondary pointing menu">
      <Link to="/" className="item">
        HOME
      </Link>
      <Link to="/streams/new" className="item">
        NEW
      </Link>
      <Link to="/streams/show" className="item">
        SHOW
      </Link>
      <Link to="/streams/edit" className="item">
        EDIT
      </Link>
      <Link to="/streams/delete" className="item">
        DELETE
      </Link>
      <div className="right menu">
        <Link to="/" className="item">
          All Streams
        </Link>
        <GoogleAuth />
      </div>
    </div>
  )
}

export default Header;