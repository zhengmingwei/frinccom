import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import createLogger from 'redux-logger';
import reducers from 'reducers';


export default function configureStore(preloadedState) {
    const store = createStore(
        reducers,
        preloadedState,
        applyMiddleware(thunkMiddleware, createLogger())
    );

    return store;
}
