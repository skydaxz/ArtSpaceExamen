package com.example.artspaceexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspaceexamen.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {

    val firstArtwork = R.drawable.girasoles_van_gogh
    val secondArtwork = R.drawable.el_grito_edvard_munch
    val thirdArtwork = R.drawable.paseo_mar_sorolla

    var title by remember{ mutableStateOf(R.string.los_girasoles_nombre) }

    var autor by remember{ mutableStateOf(R.string.los_girasoles_autor) }

    var year by remember{ mutableStateOf(R.string.los_girasoles_anyo) }

    var currentArt by remember { mutableStateOf(firstArtwork) }

    var expanded by remember { mutableStateOf(false) }

    Column{
        Card(
            modifier = modifier
                .clip(MaterialTheme.shapes.large)
                .padding(dimensionResource(id = R.dimen.padding_big)),

            ){
            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.space_big)))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ArtworkImage(currentArt = currentArt)
                ExpandedButton(expanded = expanded, onClick = {expanded = !expanded})
                if(expanded){
                    ArtworkTitle(title = title, year = year, autor = autor)
                    Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.space_medium)))
                }
            }
        }
        Row(
            modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(0.1f))
            //Button Anterior
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    when(currentArt){
                        firstArtwork -> {
                            currentArt = thirdArtwork
                            title = R.string.paseo_mar_nombre
                            autor = R.string.paseo_mar_autor
                            year = R.string.paseo_mar_anyo
                        }
                        secondArtwork -> {
                            currentArt = firstArtwork
                            title = R.string.los_girasoles_nombre
                            autor = R.string.los_girasoles_autor
                            year = R.string.los_girasoles_anyo
                        }
                        else -> {
                            currentArt = secondArtwork
                            title = R.string.el_grito_nombre
                            autor = R.string.el_grito_autor
                            year = R.string.el_grito_anyo
                        }
                    }
                }) {
                Text(
                    text = stringResource(id = R.string.boton_anterior),
                    style = MaterialTheme.typography.displayMedium,
                )
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.space_big)))
            //Button Siguiente
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    when(currentArt){
                        firstArtwork -> {
                            currentArt = secondArtwork
                            title = R.string.el_grito_nombre
                            autor = R.string.el_grito_autor
                            year = R.string.el_grito_anyo
                        }
                        secondArtwork -> {
                            currentArt = thirdArtwork
                            title = R.string.paseo_mar_nombre
                            autor = R.string.paseo_mar_autor
                            year = R.string.paseo_mar_anyo
                        }
                        else -> {
                            currentArt = firstArtwork
                            title = R.string.los_girasoles_nombre
                            autor = R.string.los_girasoles_autor
                            year = R.string.los_girasoles_anyo
                        }
                    }
                }) {
                Text(
                    text = stringResource(id = R.string.boton_siguiente),
                    style = MaterialTheme.typography.displayMedium,
                )
            }
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}
@Composable
fun ArtworkImage(@DrawableRes currentArt: Int, modifier: Modifier = Modifier){
    Image(
        painter = painterResource(currentArt),
        contentDescription = stringResource(R.string.los_girasoles_nombre),
        modifier = modifier
            .fillMaxWidth()
            .size(dimensionResource(id = R.dimen.image_size))
    )
}
@Composable
fun ArtworkTitle(@StringRes title: Int,  @StringRes year: Int, @StringRes autor: Int){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
        )
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.etiqueta_autor),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(autor),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.etiqueta_anyo),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(year),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}
@Composable
private fun ExpandedButton(
    expanded: Boolean,
    onClick: () ->Unit,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
    ) {
        Text(
            text = stringResource(id = R.string.detalles_cuadro),
            style = MaterialTheme.typography.labelSmall,
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
        )
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.space_big)))
        IconButton(
            onClick = onClick,
            modifier = modifier,
        ){
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = stringResource(R.string.detalles_cuadro),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}