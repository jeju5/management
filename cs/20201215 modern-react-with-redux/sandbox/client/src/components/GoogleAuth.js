import React from 'react';
import { connect } from 'react-redux';
import { signIn, signOut } from '../actions/actions';

class GoogleAuth extends React.Component {

  componentDidMount() {
    window.gapi.load('client:auth2', () => {
      window.gapi.client.init({
        clientId: 'x',
        scope: 'email'
      }).then(() => {
        this.auth = window.gapi.auth2.getAuthInstance();
        this.onAuthChange(this.auth.isSignedIn.get());
        this.auth.isSignedIn.listen(this.onAuthChange);
      })
    });
  }

  onAuthChange = (isSignedIn) => {
    if (isSignedIn) {
      this.props.signIn();
    } else {
      this.props.singOut();
    }
  }

  renderAuthButton = () => {
    if (this.props.isSignedIn === null) {
      return <div>Please Sign In</div>;
    } else if (this.props.isSignedIn === true) {
      return <button onClick={ () => {this.auth.signOut()} }>sign out</button>
    } else {
      return <button onClick={ () => {this.auth.signIn()} }>sign in</button>
    }
  }

  render() {
    return this.renderAuthButton()
  }
};

const mapStateToProp = (state) => {
  return {
    isSignedIn: state.isSignedIn
  }
}

export default connect(
  mapStateToProp,
  {
    signIn,
    signOut
  }
)(GoogleAuth);



// elWR8nmr4hfIKNnMe-3H5RGo