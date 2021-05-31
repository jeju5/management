import React from 'react';
import { Route, BrowserRouter } from 'react-router-dom';
import StreamShow from './stream/StreamShow';
import StreamList from './stream/StreamList';
import StreamCreate from './stream/StreamCreate';
import StreamEdit from './stream/StreamEdit';
import StreamDelete from './stream/StreamDelete';
import Header from './Header';

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Header />
          <Route path="/" exact component={StreamList} />
          <Route path="/streams" exact component={StreamList} />
          <Route path="/streams/new" exact component={StreamCreate} />
          <Route path="/streams/show" exact component={StreamShow} />
          <Route path="/streams/edit" exact component={StreamEdit} />
          <Route path="/streams/delete" exact component={StreamDelete} />
        </div>
      </BrowserRouter>
    </div>
  )
}

export default App;