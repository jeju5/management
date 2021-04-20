import { useEffect, useState } from "react";

const Route = ( {path, children } ) => {

  const [currentPath, setCurrentPath] = useState(window.location.pathname);

  const onPathChange = () => {
    console.log("CHANGED" + window.location.pathname);
    setCurrentPath(window.location.pathname); // this updates a state just to trigger 'render' (state update -> render)
  }

  useEffect( () => {
    window.addEventListener('popstate', onPathChange);

    return () => {
      // we don't want to keep adding the event listener everytime it re-renders
      window.removeEventListener('popstate', onPathChange);  
    }
  }, []) // run an effect only once;

  return (
    currentPath === path ? children
                         : null
  );
};

export default Route;