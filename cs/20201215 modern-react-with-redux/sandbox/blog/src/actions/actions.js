import { jsonplaceholder } from '../apis/apis'
import _ from 'lodash';

// invalid approach of actionCreator directly making api calls.
export const fetchPostsNaive = async () => {
    const response = await jsonplaceholder.get('/posts');

    return {
        type: "FETCH_POST",
        payload: response
    };
};

// valid approach of returning async dispatcher-function that makes api calls.
export const fetchPosts = () => {
    return (
        async (dispatch) => {
            const response = await jsonplaceholder.get('/posts');
            dispatch({
                type: "FETCH_POST",
                payload: response.data
            });
    });
};

export const fetchUser = (id) => {
    return (
        async (dispatch) => {
            const response = await jsonplaceholder.get(`/users/${id}`)
            dispatch({
                type: "FETCH_USER",
                payload: response.data
            })
        }
    )
}

export const fetchUserWithMemoization = (id) => (dispatch) => { _fetchUser(id, dispatch) };

const _fetchUser = _.memoize(
    async (id, dispatch) => {
       const response = await jsonplaceholder.get(`/users/${id}`)
       dispatch({
           type: "FETCH_USER",
           payload: response.data
       })
    }
);

export const fetchPostsAndUsers = () => async (dispatch, getState) => {
    await dispatch(fetchPosts());  // fetchPosts() is a dispatcher-function that dispatches FETCH_POST action. FETCH_POST will be dispatched.

    // get unique userIds from posts
    const userIds = getState().posts.map(p => p.userId);
    const uniqueUserIds = _.uniq(userIds);

    // fetch user
    uniqueUserIds.forEach(
        id => dispatch(fetchUser(id)) // // fetchUser() is a dispatcher-function that dispatches FETCH_USER action. FETCH_USER will be dispatched.
    );
}