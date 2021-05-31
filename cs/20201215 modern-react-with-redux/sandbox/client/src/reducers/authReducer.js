const authReducer = (
  state = {
    isSignedIn: null
  }, 
  action
) => {

  switch(action.type) {
    case "SIGN_IN":
      return {
        isSignedIn: true
      }
    case "SIGN_OUT":
      return {
        isSignedIn: false
      }
    default:
      return state;
  }
}

export default authReducer;