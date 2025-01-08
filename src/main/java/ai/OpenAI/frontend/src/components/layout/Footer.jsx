function Footer() {
    return (
        <footer className="bg-gray-800 text-white w-full py-4">
            <div className="max-w-7xl mx-auto px-4">
                <div className="flex justify-between items-center">
                    <div>
                        <div className="flex items-center space-x-2 mb-4">
                            <h2 className="text-2xl font-bold">Tech Blocks</h2>
                        </div>
                    </div>
                    <div className="flex space-x-6 text-sm">
                        <a href="#" className="hover:underline">
                            Privacy Policy
                        </a>
                        <a href="#" className="hover:underline">
                            Terms & Conditions
                        </a>
                        <a href="#" className="hover:underline">
                            Cookie Policy
                        </a>
                        <a href="#" className="hover:underline">
                            Contact
                        </a>
                    </div>
                </div>
            </div>
        </footer>
    )
}

export default Footer
