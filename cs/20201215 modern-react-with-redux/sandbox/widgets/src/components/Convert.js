import React, {useState, useEffect} from 'react';
import axios from 'axios';

const googleTranslateApiKey = "AIzaSyCHUCmpR7cT_yDFHC98CZJy2LTms-IwDlM";

const Convert = ({ language, text }) => {
  const [translated, setTranslated] = useState('')
  const [finalizedText, setFinalizedText] = useState(text);

  // 'text is being entered' => set/cancel a timer to update 'finalized text'
  useEffect(
    () => {
      console.log("#1")
      // set a timer to update 'finalized text'
      const timerId = setTimeout(
        () => {
          setFinalizedText(text);
        },
        2000
      );

      // cancel a timer you created above because 'text' is newly updated
      return (
        () => {
          clearTimeout(timerId);
        }
      )
    },
    [text]
  );

  // 'text is finalized or language is selected' => make api call and update translated.
  useEffect(
    () => {
      console.log("#2")
      const translate = async () => {
        const { data } = await axios.post(
          'https://translation.googleapis.com/language/translate/v2',
          {},
          {
            params: {
              q: finalizedText,
              target: language.value,
              key: googleTranslateApiKey
            }
          }
        );
        setTranslated(data.data.translations[0].translatedText);
      }

      if (finalizedText) {
        translate();
      }
    },
    [language, finalizedText]
  );

  return (
    <div>
      <h1 className="ui header">{translated}</h1>
    </div>
  );
}

export default Convert;