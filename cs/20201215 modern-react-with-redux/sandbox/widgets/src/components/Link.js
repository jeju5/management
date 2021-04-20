import React from 'react';

const Link = ({ className, href, children}) => {

  const onClickLink = (event) => {
    if (event.metaKey || event.ctrlKey) {
      // if cmdkey or ctrl key is pressed -> just do regurlar stuff
      return;
    }

    event.preventDefault();
    window.history.pushState({}, '', href);
    window.dispatchEvent(new PopStateEvent('popstate'));
  }

  return (
    <a className={className} href={href} onClick={onClickLink}>
      {children}
    </a>
  );
};

export default Link;