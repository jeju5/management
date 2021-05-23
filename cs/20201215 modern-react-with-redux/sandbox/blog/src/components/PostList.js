import React from 'react';
import { connect } from 'react-redux';
import { fetchPostsThunk } from '../actions/actions'

class PostList extends React.Component {

    componentDidMount() {
        this.props.fetchPostsThunk();
    }

    render() {
        return <div>Post List</div>
    }
}

export default connect(
    null,
    { fetchPostsThunk }
)(PostList)