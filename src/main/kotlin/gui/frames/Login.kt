package gui.frames

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class Login() : JFrame(), ActionListener {

    private var userLabel = JLabel("Username")
    private var passwordLabel = JLabel("Password")
    private var userTextField = JTextField()
    private var passwordField = JPasswordField()
    private var loginButton = JButton("Login")
    private var resetButton = JButton("Reset")
    private var showPassword = JCheckBox("Show Password")

    init {
        isVisible = true

        setLayoutManager()
        setLocationAndSize()
        addComponentsToContainer()
        addActionEvent()
    }

    private fun setLayoutManager() {
        layout = null
    }

    private fun setLocationAndSize() {
        userLabel.setBounds(250, 200, 100, 30)
        userTextField.setBounds(350, 200, 200, 30)
        passwordLabel.setBounds(250, 270, 100, 30)
        passwordField.setBounds(350, 270, 200, 30)
        showPassword.setBounds(350, 300, 150, 30)
        loginButton.setBounds(275, 350, 100, 30)
        resetButton.setBounds(425, 350, 100, 30)
    }

    private fun addComponentsToContainer() {
        add(userLabel)
        add(passwordLabel)
        add(userTextField)
        add(passwordField)
        add(showPassword)
        add(loginButton)
        add(resetButton)
    }

    private fun addActionEvent() {
        loginButton.addActionListener(this)
        resetButton.addActionListener(this)
        showPassword.addActionListener(this)
        loginButton.addActionListener(this)
    }

    override fun actionPerformed(event: ActionEvent) {
        when (event.source) {
            loginButton -> {
                val username = userTextField.text

            }

            resetButton -> {
                userTextField.text = ""
                passwordField.text = ""
            }

            showPassword -> {
                if (showPassword.isSelected)
                    passwordField.echoChar = 0.toChar()
                else
                    passwordField.echoChar = '*'
            }
        }
    }
}