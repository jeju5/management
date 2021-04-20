import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Search = () => {
  const [term, setTerm] = useState("first word");
  const [results, setResults] = useState([]);

  /*
  you can't directly put async function as the first arg of useEffect
  useEffect(async () => {
    //
  }, [term]);
  */
  useEffect(() => {
    const getWikipedia = async () => {
      // https://en.wikipedia.org/w/api.php?action=query&list=search&origin=*&format=json&srsearch='queryTerm'
      const {data} = await axios.get(
        'https://en.wikipedia.org/w/api.php',
        {
          params: {
            action: 'query',
            list: 'search',
            origin: '*',
            format: 'json',
            srsearch: term
          }
        }
      );
      
      setResults(data.query.search);
    };

    if (term && results.length === 0) {
      // do the initial search right away
      getWikipedia();
    } else {
      // do the non-initial searches with timeout
      const timeOutId = setTimeout(() => {
        if (term) {
          getWikipedia();
        }
      }, 1500);
  
      const expireTimeOut = () => {
        clearTimeout(timeOutId);
      }
  
      return expireTimeOut;
    }
    
  }, [term]);

  const renderedResults = results.map((r) => {  
    return (
      <div key={r.pageid} className="item">
        <div className="right floated content">
          <a
            className="ui button"
            href={`https://en.wikipedia.org/?curid=${r.pageid}`}
          >
              Go
          </a>
        </div>
        <div className="content">
          <div className="header">
            {r.title}
          </div>
          <div dangerouslySetInnerHTML={{ __html: r.snippet}} />
        </div>
      </div>
    );
  })

  return(
    <div>
      <div className="ui form">
        <div className="field">
          <label>Enter Search Term</label>
          <input
            value={term}
            onChange={(e) => setTerm(e.target.value)}
            className="input"
          />
        </div>
      </div>
      <div className="ui celled list">
        {renderedResults}
      </div>
    </div>
  );
}

export default Search;