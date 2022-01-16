package com.mutualmobile.feat.githubrepos.ui.github.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.mutualmobile.feat.githubrepos.nav.GithubNavigation
import com.mutualmobile.feat.githubrepos.ui.model.UIRepo
import com.mutualmobile.praxis.commonui.theme.PraxisTheme
import com.mutualmobile.praxis.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a single Repo detail screen.
 */
@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

  @Inject
  lateinit var navigator: Navigator

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = ComposeView(requireContext()).apply {

    setContent {
      // Create a Compose MaterialTheme inheriting the existing colors, typography
      // and shapes of the current View system's theme
      PraxisTheme {
        GithubNavigation(
          navigator = navigator,
          uiRepo = arguments?.getParcelable("uiRepoModel") as UIRepo?
        )
      }
    }
  }
}
