import { Routes, Route } from 'react-router-dom'
import Layout from './components/layout/Layout.jsx'
import NotFound from './pages/NotFound.jsx'
import Chat from './pages/Chat.jsx'
import ChatRoomList from './pages/ChatRoomList.jsx'
import ChatRoom from './pages/ChatRoom.jsx'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

function App() {
    return (
        <Layout>
            <Routes>
                <Route path="/" element={<ChatRoomList />} />
                <Route path="/chat/:roomId" element={<ChatRoom />} />
                <Route path="/chat/ai" element={<Chat />} />

                <Route path="*" element={<NotFound />} />
            </Routes>
            <ToastContainer position="top-right" autoClose={3000} hideProgressBar={false} closeOnClick pauseOnHover />
        </Layout>
    )
}

export default App
