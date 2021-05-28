import { jsonplaceholder } from '../apis/apis'

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