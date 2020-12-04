# Session 1
* What is App Function?
  * App function is a react component that returns jsx.
  * JSX is javascript xml. It directs reacts to show dom elements or react components. react components are in /component directory.
    ```
    export default function App() {
      const [language, setLanguage] = useState("ru");
      const [text, setText] = useState("");

      return (
        <div>
          <Field label="Enter English" onChange={setText} value={text} />
          <Languages language={language} onLanguageChange={setLanguage} />
          <hr />
          <Translate text={text} language={language} />
        </div>
      );
    }
    ```
