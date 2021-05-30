import { combineReducers } from 'redux';
import postsReducer from './postsReducer';
import usersReducer from './usersReducer';

const actionReducer = (state = {}, action) => {
    if (action.type === "THUNK_TEST") {
        console.log(action.payload);
    }

    return state;
}

export default combineReducers({
    posts: postsReducer,
    users: usersReducer,
    actions: actionReducer
});