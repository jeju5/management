import { combineReducers } from 'redux';

const songsReducer = () => {
    return [
        {title: "Surfing", duration: "4:01"},
        {title: "Safari", duration: "5:01"},
        {title: "Starkist", duration: "3:01"},
        {title: "Milk", duration: "1:01"}
    ];
}

const selectedSongReducer = (selectedSong = null, action) => {
    if (action.type === "SONG_SELECTED") {
        return action.payload;
    }

    return selectedSong;
}

export default combineReducers({
    songs: songsReducer,
    selectedSong: selectedSongReducer
})