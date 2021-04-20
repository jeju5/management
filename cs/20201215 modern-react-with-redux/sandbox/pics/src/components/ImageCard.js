import React from 'react';

class ImageCard extends React.Component {

  constructor(props) {
    super(props);
    this.imageRef = React.createRef();

    this.state = {
      spans: 0
    };
  }

  /* after this ImageCard is mounted on UI */
  componentDidMount() {
    // imageReft is { current: img }
    console.log(this.imageRef);
    // this is 0 because you haven't loaded img yet. ImageCard is mounted but not img.
    console.log(this.imageRef.current.clientHeight);

    // imageRef.current is <img>. Let's add a load listener on the <img>
    this.imageRef.current.addEventListener('load', this.setSpans);
  }

  setSpans = () => {
    const height = this.imageRef.current.clientHeight;
    /*
    Math.ceil(1.12) => 2  'touch the ceiling'
    Math.ceil(1) => 1  'ceiling already touched the ceiling'
    Math.ceil(-1.12) => -1  'touch the ceiling. ceiling is at above'
    Math.ceil(-1) => -1  'touch the ceiling. ceiling is at above'
    Math.floor(1.12) => 1 'touch the floor'
    */
    const calculatedSpans = Math.ceil(height / 10) // If the value is 150.1, you want to give two spans
    this.setState({ spans: calculatedSpans})
  }

  render() {
    const { description, urls} = this.props.image;
    
    return (
      <div style={{ gridRowEnd: `span ${this.state.spans}` }}>
        <img
          ref={this.imageRef}
          alt={description}
          src={urls.regular}
        />
      </div>
    );
  }
}

export default ImageCard;