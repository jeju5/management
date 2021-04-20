import React, { useState } from 'react';
import Dropdown from './Dropdown.js'
import Convert from './Convert.js'

const languages = [
  {
    label: 'Korean',
    value: 'ko'
  },
  {
    label: 'Japanese',
    value: 'ja'
  },
  {
    label: 'Afrikaans',
    value: 'af'
  }
];

const Translate = () => {
  const [language, setLanguage] = useState(languages[0]);
  const [text, setText] = useState('');

  return (
    <div className="ui form translate">
      <div className="field">
        <label>Enter Text</label>
        <input value={text} onChange={(e) => setText(e.target.value)} />
      </div>
      <Dropdown
        label="Select a Language"
        options={languages}
        selected={language}
        onSelect={setLanguage}
      />
      <hr />
      <h3 className="ui header">Output</h3>
      <Convert text={text} language={language} />
    </div>
  )
};

export default Translate;

