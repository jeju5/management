import { fetchedJson } from '../apis/apis'

export const fetchPosts = async () => {
    const response = await fetchedJson.get('/posts');

    return {
        type: "FETCH_POST",
        payload: response
    };
};


export const fetchPostsThunk = () => {

    return ( async (dispatch) => {
      const response = await fetchedJson.get('/posts');

      dispatch({
        type: "FETCH_POST",
        payload: response
      })
    });
};