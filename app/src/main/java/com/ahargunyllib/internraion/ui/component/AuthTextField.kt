package com.ahargunyllib.internraion.ui.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier


//@Composable
//fun AuthTextField(
//    value: String,
//    onValueChanged: (String) -> Unit,
//    modifier: Modifier = Modifier,
//
//    ) {
//    Card(
//        elevation = CardDefaults.cardElevation(10.dp)
//    ){
//        BasicTextField(value = , onValueChange = )
//    }
//
//}

//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//import com.ahargunyllib.internraion.ui.theme.Black
//import com.ahargunyllib.internraion.ui.theme.White
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AuthTextField(
//    text: String,
//    placeholder: String,
//    trailingIcon: ImageVector? = null,
//    showPassword: Boolean = false,
//    onValueChange: (String) -> Unit = {},
//    onPasswordToggle: (Boolean) -> Unit = {},
//    label: String? = null,
//    isPassword: Boolean = false,
//    isNumeric: Boolean = false,
//    maxLine: Int = 1,
//    minLine: Int = 1,
//    onIconClick: () -> Unit = {},
//    modifier: Modifier = Modifier.fillMaxWidth()
//) {
//    Card(
//        elevation = CardDefaults.cardElevation(10.dp)
//    ) {
//        OutlinedTextField(
//            modifier = modifier,
//            value = text,
//            onValueChange = {
//                onValueChange(it)
//            },
//            placeholder = {
//                Text(text = placeholder)
//            },
//            trailingIcon = {
//                if (trailingIcon != null) {
//                    if (isPassword) {
//                        if (!showPassword) {
//                            IconButton(onClick = { onPasswordToggle(!showPassword) }) {
//                                Icon(
//                                    imageVector = Icons.Default.VisibilityOff,
//                                    contentDescription = "show",
//                                    Modifier
//                                        .size(25.dp)
//                                        .clickable { onPasswordToggle(!showPassword) },
//                                    tint = White
//                                )
//                            }
//                        } else {
//                            IconButton(onClick = { onPasswordToggle(!showPassword) }) {
//                                Icon(
//                                    imageVector = Icons.Default.Visibility,
//                                    contentDescription = "",
//                                    Modifier
//                                        .size(25.dp)
//                                        .clickable { onPasswordToggle(!showPassword) },
//                                    tint = White
//                                )
//                            }
//                        }
//                    } else {
//                        IconButton(onClick = { onIconClick() }) {
//                            Icon(
//                                imageVector = trailingIcon,
//                                contentDescription = "",
//                                Modifier
//                                    .size(25.dp)
//                                    .clickable { onPasswordToggle(!showPassword) },
//                                tint = White
//                            )
//                        }
//                    }
//                }
//            },
//            shape = RoundedCornerShape(10.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                unfocusedIndicatorColor = White,
//                focusedIndicatorColor = White,
//                cursorColor = White,
//                focusedLabelColor = Black,
//                unfocusedLabelColor = Black
//            ),
//            label = {
//                label?.let {
//                    Text(text = it)
//                }
//            },
//            visualTransformation =
//            if (isPassword) {
//                if (showPassword) {
//                    VisualTransformation.None
//                } else {
//                    PasswordVisualTransformation()
//                }
//            } else {
//                VisualTransformation.None
//            },
//            keyboardOptions =
//            if (isPassword) {
//                KeyboardOptions(keyboardType = KeyboardType.Password)
//            } else if (isNumeric) {
//                KeyboardOptions(keyboardType = KeyboardType.Number)
//            } else {
//                KeyboardOptions(keyboardType = KeyboardType.Text)
//            },
//            maxLines = maxLine,
//            minLines = minLine
//        )
//    }
//}